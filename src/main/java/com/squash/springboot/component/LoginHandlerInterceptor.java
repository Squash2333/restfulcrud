package com.squash.springboot.component;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//登录检查
public class LoginHandlerInterceptor implements HandlerInterceptor {
    //目标方法执行之前
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object handler)throws ServletException, IOException {
        Object user=request.getSession().getAttribute("loginUser");
        if(user==null){
            //未登录，返回登陆页面
            request.setAttribute("msg","没有权限，请先登陆！");
            request.getRequestDispatcher("/index.html").forward(request,response);
            return false;
        }else{
            //已登录，放行请求
            return true;
        }

    }
}
