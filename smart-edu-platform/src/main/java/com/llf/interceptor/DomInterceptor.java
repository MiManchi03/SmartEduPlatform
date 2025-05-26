package com.llf.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Component
public class DomInterceptor implements HandlerInterceptor {
    @Override
    //视图渲染完毕后执行，最后执行
    /*由于曾经前后端是不分离的，因此前端的图片渲染是执行在后端的，当后端的图片渲染成功后就会调用这个方法
     * 但是现在前后端分离，所以这个方法不再使用*/
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("afterCompletion...");
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

    @Override
    //目标资源方法执行后执行
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        log.info("postHandle...");
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    //目标资源方法执行前执行，放回true：放行，返回false：不放行
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("preHandle...");
        //return HandlerInterceptor.super.preHandle(request, response, handler);
        return true;//始终放行
    }
}
