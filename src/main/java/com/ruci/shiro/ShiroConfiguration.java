package com.ruci.shiro;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;

@Configuration
public class ShiroConfiguration {

    @Bean()
    //自定义拦截器
    public ShiroFilterFactoryBean shiroFilter(@Qualifier("securityManager")SecurityManager securityManager){
        ShiroFilterFactoryBean bean=new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager);

        //登录地址
        bean.setLoginUrl("/login");
        //登录后地址
        bean.setSuccessUrl("/index");
        //无权访问跳转的页面
        bean.setUnauthorizedUrl("/unanthorized");

        /**
         * Shiro内置过滤器，可以实现权限相关的拦截器
         *  常用的过滤器：
         *      anon: 无需认证（登录）可以访问
         *      authc: 必须认证才可以访问
         *      user: 如果使用rememberMe功能可以直接访问
         *      perms: 该资源必须得到资源权限才可以访问
         *      role: 该资源必须得到角色权限才可以访问
         */

        LinkedHashMap<String ,String > map=new LinkedHashMap<>();
        map.put("/index","authc"); //必须认证才能访问
        map.put("/","authc");
        map.put("/login","anon");  //不需要认证
        map.put("/dist/**","anon"); //不存在static目录，因此放行子目录
        map.put("/static/**","anon"); //不存在static目录，因此放行子目录
        map.put("/tologin","anon");   //不需要认证
        map.put("/tosava","anon");   //不需要认证
        map.put("/sava","anon");   //不需要认证
        map.put("/test","anon");   //不需要认证
        map.put("/**","user");   //必须登录
        map.put("/admin","roles[admin]"); //必须用 amdin 角色才能访问
        map.put("/edit","perms[edit]"); //必须用有 permission 权限的角色才能访问

        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

    @Bean("securityManager")
    public SecurityManager securityManager(@Qualifier("authRealm")AuthRealm authRealm){
        //注入自定义的授权和认证
        DefaultWebSecurityManager manager=new DefaultWebSecurityManager();
        manager.setRealm(authRealm);

        return manager;
    }

    @Bean("authRealm")
    public AuthRealm authRealm(@Qualifier("credentialMatcher")CredentialMatcher credentialMatcher){
        AuthRealm authRealm=new AuthRealm();
        //获取自定义的密码校验器
        authRealm.setCredentialsMatcher(credentialMatcher);

        return authRealm;
    }

    @Bean("credentialMatcher")
    //把自定义密码验证注入spring中
    public CredentialMatcher credentialMatcher(){
        return new CredentialMatcher();
    }

    @Bean("authorizationAttributeSourceAdvisor")
    //实现shiro和springboot的关联
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(@Qualifier("securityManager")SecurityManager securityManager){
        AuthorizationAttributeSourceAdvisor author=new AuthorizationAttributeSourceAdvisor();
        author.setSecurityManager(securityManager);
        return author;
    }

    @Bean
    //开启spirngboot代理
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator(){
        DefaultAdvisorAutoProxyCreator creator=new DefaultAdvisorAutoProxyCreator();
        creator.setProxyTargetClass(true);
        return creator;
    }

    @Bean
    //开启thymeleaf验证的支持
    public ShiroDialect getShiroDialect(){
        return new ShiroDialect();
    }
}
