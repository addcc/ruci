package com.ruci.service;

import com.ruci.dao.RiLoginMapper;
import com.ruci.dao.UserRoleMapper;
import com.ruci.domain.RiLogin;
import com.ruci.domain.UserRole;
import com.ruci.exception.ParamException;
import com.ruci.param.LoginParam;
import com.ruci.util.MD5Util;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class LoginService {

    @Autowired
    private RiLoginMapper loginMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private UserRoleMapper userRoleMapper;


//    /**
//     * 登录校验
//     * @param name 账号
//     * @param password 密码
//     */
//    public void login(String name,String password){
//        RiLogin login=loginMapper.selectByName(name);
//        if(login==null){
//            throw new ParamException("用户不存在");
//        }
//
//        String pass= MD5Util.inputPassToDbPass(password,"1a2b3c4d");
//
//        if(!StringUtils.equals(login.getPassword(),pass)){
//            throw new ParamException("密码错误");
//        }
//
//    }

    public RiLogin findByUsername(String loginName){

        RiLogin login= loginMapper.findByUsername(loginName);
        return login;
    }


    @Transactional
    public void sava(LoginParam param){
        RiLogin login=loginMapper.selectByName(param.getUsername());
        if(login!=null){
            throw new ParamException("该账号已被使用");
        }



        RiLogin user=new RiLogin();
        user.setLoginName(param.getUsername());
        user.setPassword(MD5Util.inputPassToDbPass(param.getPassword(),"1a2b3c4d"));

        //新增登录账号
        loginMapper.insert(user);
        //新增账号资料
        userService.sava(user.getUid());
        //添加默认权限
        UserRole userRole=new UserRole();
        userRole.setUid(user.getUid());
        userRole.setRid(1);
        userRoleMapper.insert(userRole);

    }

    //更新账号信息
    public void upadate(LoginParam param){

        RiLogin login=new RiLogin();
        BeanUtils.copyProperties(param,login);

       loginMapper.updateById(login);

    }
//
//    @Transactional
//    public void delete(Integer id){
//        RiLogin login=loginMapper.selectById(id);
//        if(login==null){
//            throw new ParamException("用户不存在");
//        }
//
//        //删除用户登录信息
//        loginMapper.deleteById(id);
//        //删除用户资料信息
//        userService.delete(id);
//
//    }
}
