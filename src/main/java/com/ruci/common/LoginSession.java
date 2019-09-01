package com.ruci.common;

import com.ruci.domain.RiLogin;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class LoginSession {

    public static Integer getLoginId(){

        //获取当前用户信息
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        RiLogin login= (RiLogin) request.getSession().getAttribute("login");

        return login.getUid();

    }
}
