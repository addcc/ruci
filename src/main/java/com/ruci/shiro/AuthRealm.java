package com.ruci.shiro;

import com.ruci.domain.Permission;
import com.ruci.domain.RiLogin;
import com.ruci.domain.RiRole;
import com.ruci.service.LoginService;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class AuthRealm extends AuthorizingRealm {

    @Autowired
    private LoginService loginService;

    //认证方法
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {

        //获取user的属性，强制转化成user属性
        RiLogin login=(RiLogin) principal.fromRealm(this.getClass().getName()).iterator().next();

        List<String> permissionList=new ArrayList<>();
        List<String> roleNameList=new ArrayList<>();
        //获取用户所拥有的角色
        Set<RiRole> roleSet=login.getRiRoles();
        //判断角色是否为空，不为空则遍历输出权限
        if(CollectionUtils.isNotEmpty(roleSet)){
            for (RiRole role : roleSet) {
                roleNameList.add(role.getRoleName());
                Set<Permission> permissions=role.getPermissions();
                //判断权限是否为空，不为空则添加到list集合中
                if(CollectionUtils.isNotEmpty(permissions)){
                    for (Permission permission : permissions) {
                        permissionList.add(permission.getName());
                    }
                }
            }
        }
        //获取该用户的所有权限
        SimpleAuthorizationInfo info=new SimpleAuthorizationInfo();
        info.addStringPermissions(permissionList);
        //添加角色授权
        info.addRoles(roleNameList);
        return info;
    }

    //登录方法
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {

        //从controller获取登录数据
        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
        String loginName=usernamePasswordToken.getUsername();
        //根据用户名称查询
        RiLogin login=loginService.findByUsername(loginName);
        //返回用户属性，密码和类名
        return new SimpleAuthenticationInfo(login,login.getPassword(),this.getClass().getName());
    }
}
