package com.txy.jpetstore.demo.interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class LoginInterceptor  implements HandlerInterceptor {
    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        ValueOperations<String, String> stringStringValueOperations = stringRedisTemplate.opsForValue();
        if (!request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/")+1). equals(stringStringValueOperations.get("loginAccount"))){
            return false;
        }
        return true;
    }
}
