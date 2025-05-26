package com.llf.interceptor;

import com.llf.utils.CurrentHolder;
import com.llf.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
@Slf4j
public class TokenInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        /*因为本身就已经是HttpServletRequest，不需要再强转*/
/*        //1. 获取请求路径
       String path = request.getServletPath();
        log.info("Interceptor拦截到的路径为:" + path);
        //2.判断路径是否为登录操作(/login)放行
        if (path.equals("/login")) {
            log.info("Interceptor拦截到了登录请求，放行");
            return true;
        }*/
        //3.获取请求头中的token
        String token = request.getHeader("token");
        //4.判断token是否存在，不存在就返回未授权状态码（401）
        if (token == null || token.isEmpty()) {// token不存在或者是空的就返回未授权状态码（401）
            log.info("Interceptor拦截到了请求，但是请求头中没有token");
            response.setStatus(401);
            return false;
        }
        //5.如果token存在就放行，就校验token-失败返回未授权状态码（401）
        try {
            Claims claims = JwtUtils.parseToken(token);//解析token
            //Integer.valueOf(claims.get("id").toString());
            Integer id = (Integer) claims.get("id");
            CurrentHolder.setCurrentId(id);
            log.info("Interceptor拦截到的id为:" + id);
        } catch (RuntimeException e) {
            log.info("Interceptor令牌非法");
            response.setStatus(401);
            return false;
        }
        //6.校验成功就放行
        log.info("Interceptor令牌合法，放行");
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        log.info("Interceptor请求处理完毕");
        CurrentHolder.remove();
    }
}
