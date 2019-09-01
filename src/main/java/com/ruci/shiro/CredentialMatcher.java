package com.ruci.shiro;

import com.ruci.util.MD5Util;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

public class CredentialMatcher extends SimpleCredentialsMatcher {

    //自定义密码验证
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {

        UsernamePasswordToken usernamePasswordToken=(UsernamePasswordToken) token;
        //获取用户输入的密码
        String password=new String (usernamePasswordToken.getPassword());
        String pass= MD5Util.inputPassToDbPass(password,"1a2b3c4d");
        //获取数据库内的密码
        String dbpassword=(String)info.getCredentials();

        //判断
        if(StringUtils.equals(pass,dbpassword)){
            return true;
        }else {
            return false;
        }

    }
}
