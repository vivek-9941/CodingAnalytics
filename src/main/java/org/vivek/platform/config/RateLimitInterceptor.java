package org.vivek.platform.config;


import io.github.bucket4j.Bucket;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class RateLimitInterceptor implements HandlerInterceptor {
    @Autowired
    RateLimiterService rateLimiterService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String clientId = request.getHeader("X-Client-Id");
        if(clientId == null || clientId.isBlank()){
            response.setStatus(HttpStatus.BAD_REQUEST.value());
            response.getWriter().write("Missing X-Client-Id header");
            return false;
        }
        Bucket bucket = rateLimiterService.resolveBucket(clientId);
        if(bucket.tryConsume(1)){
            return true;
        }
        else{
            response.setStatus(HttpStatus.TOO_MANY_REQUESTS.value());
            response.getWriter().write("Too many requests");
            return false;
        }
    }

}
