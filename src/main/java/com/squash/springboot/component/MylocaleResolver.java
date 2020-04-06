package com.squash.springboot.component;

import org.springframework.web.servlet.LocaleResolver;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Locale;

//可以在链接上携带区域信息
public class MylocaleResolver implements LocaleResolver {
    @Override
    public Locale resolveLocale(HttpServletRequest request){
        String l =request.getParameter("l");
        Locale locale=Locale.getDefault();
        if(!StringUtils.isEmpty(l)){
            String[] split=l.split("_");
            locale=new Locale(split[0],split[1]);

        }
        return locale;
    }

    @Override
    public void setLocale(HttpServletRequest request, HttpServletResponse response,Locale locale){

    }
}
