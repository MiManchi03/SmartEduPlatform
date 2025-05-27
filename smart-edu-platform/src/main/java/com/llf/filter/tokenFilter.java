package com.llf.filter;

import com.llf.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class tokenFilter implements Filter {

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        System.out.println("httpRequest里面的内容为:" + httpRequest);
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        System.out.println("httpResponse里面的内容为:" + httpResponse);
        //1. 获取请求路径
        String path = httpRequest.getServletPath();
        log.info("Filter拦截到的路径为:" + path);
        //2.判断路径是否为登录操作(/login)放行
        if (path.equals("/login")) {
            log.info("Filter拦截到了登录请求，放行");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        //3.获取请求头中的token
        String token = httpRequest.getHeader("token");
        //4.判断token是否存在，不存在就返回未授权状态码（401）
        if (token == null || token.isEmpty()) {// token不存在或者是空的就返回未授权状态码（401）
            log.info("Filter拦截到了请求，但是请求头中没有token");
            httpResponse.setStatus(401);
            return;
        }
        //5.如果token存在就放行，就校验token-失败返回未授权状态码（401）
        try {
            JwtUtils.parseToken(token);
        } catch (RuntimeException e) {
            log.info("Filter令牌非法");
            httpResponse.setStatus(401);
            return;
        }
        //6.校验成功就放行
        log.info("Filter令牌合法，放行");
        filterChain.doFilter(servletRequest, servletResponse);
    }

}
