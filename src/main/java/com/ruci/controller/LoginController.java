package com.ruci.controller;

import com.ruci.common.JsonData;
import com.ruci.domain.RiLogin;
import com.ruci.exception.ParamException;
import com.ruci.param.LoginParam;
import com.ruci.service.LoginService;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;


@Controller
//@CrossOrigin //处理ajax跨区错误的问题
public class LoginController {

    @Autowired
    private LoginService loginService;
    String msg;




    //首页
    @RequestMapping("/index")
    public String index(Model model,LoginParam login){
        model.addAttribute("login",login);
        return "/index";
    }

    //登录页面
    @RequestMapping("/login")
    public String login(Model model){
        model.addAttribute("msg",msg);
        model.addAttribute("loginParam",new LoginParam());

        return "login";
    }

    //登录验证
    @RequestMapping(value = "/tologin",method = RequestMethod.POST)
    public String to(@Valid LoginParam param, BindingResult result, Model model, HttpSession session){
        if(result.hasErrors()){
            msg=result.getFieldError().getDefaultMessage();
            model.addAttribute("msg",msg);
            return "redirect:/login";
        }
        UsernamePasswordToken token=new UsernamePasswordToken(param.getUsername(),param.getPassword());
        Subject subject= SecurityUtils.getSubject();

        try{
            subject.login(token);
            RiLogin login=(RiLogin) subject.getPrincipal();
            session.setAttribute("login",login);
            return "redirect:/index";
        }catch (Exception e){
            return "redirect:/login";

        }

    }

    //退出登录
    @RequestMapping("/logout")
    public String logout() {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null) {
            subject.logout();
        }
        return "redirect:/login";
    }

    //注册验证
    @RequestMapping(value = "/sava",method = RequestMethod.POST)
    public String sava(@Valid LoginParam login,BindingResult result,Model model){
        if(result.hasErrors()){
            msg=result.getFieldError().getDefaultMessage();
            model.addAttribute("msg",msg);
            return "redirect:/tosava";
        }

        if(!StringUtils.equals(login.getPass(),login.getPassword())){
            throw new ParamException("两次输入的密码必须一致");
        }
        loginService.sava(login);
        return "redirect:/login";

    }

    //注册页面
    @RequestMapping("/tosava")
    public String tosava(Model model){

        model.addAttribute("msg",msg);
        model.addAttribute("login",new LoginParam());
        return "sava";
    }










}
