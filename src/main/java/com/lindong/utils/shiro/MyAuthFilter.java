package com.lindong.utils.shiro;


import org.apache.shiro.web.servlet.AdviceFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class MyAuthFilter extends AdviceFilter {

    @Override
    protected boolean preHandle(ServletRequest request, ServletResponse response) throws Exception {
        HttpServletRequest req = (HttpServletRequest)request;
        HttpServletResponse res = (HttpServletResponse)response;
        String isValue = (String)req.getSession().getAttribute("username");
        if (!isRule(req,res)){
            if (isValue == null){
                System.out.println("未登录,跳转登录页面.....");
                res.sendRedirect(req.getContextPath()+"/stair/admin/login");
            }else {
                return true;
            }
        }
        return false;
    }

    private boolean isRule(HttpServletRequest request, HttpServletResponse response) {
        String urlString = request.getRequestURI();
        System.out.println("执行了.....");
        if(urlString.endsWith("login")){
            return Boolean.TRUE;
        }else {
            return Boolean.FALSE;
        }
    }
}
