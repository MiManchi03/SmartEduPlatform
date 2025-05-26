package com.llf.filter;


import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

//@WebFilter(urlPatterns = "/*")
@Slf4j
public class DmoFilter implements Filter {
    /*初始化方法，web服务器启动的时候执行一次，只执行一次*/
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info("init初始化...");
    }

    /*拦截请求之后执行，会执行多次*/
    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.info("拦截到了请求...");
        // 放行
        filterChain.doFilter(servletRequest, servletResponse);
    }

    /*初始化方法，web服务器关闭的时候执行一次，只执行一次*/
    @Override
    public void destroy() {
        log.info("destroy销毁...");
    }
}
