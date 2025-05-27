package com.llf.config;

import com.llf.interceptor.DomInterceptor;
import com.llf.interceptor.TokenInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/*配置类*/
/*因为@Configuration底层封装的有@Component，所以只加个@Component也是可以的
 * 但是为了更直观的表示这是个配置类，因此最好加上@Configuration*/
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Autowired
    private TokenInterceptor tokenInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        /*解释：需要用到哪个拦截器，就添加哪个拦截器的对象，
        添加对应拦截器后再使用链式编程的思想再在后面加上需要进行拦截的请求路径*/
        //registry.addInterceptor(new DomInterceptor()).addPathPatterns("/**");
        /*因为拦截器类上已经加了@Component，即交给了ioc容器，
        所以不需要每次都创建了，只需要直接将对应拦截器的类注入进来就行*/
        registry.addInterceptor(tokenInterceptor)
                .addPathPatterns("/**")//拦截所有请求
                .excludePathPatterns("/login");//表示不拦截login的请求，即放行
    }
}
