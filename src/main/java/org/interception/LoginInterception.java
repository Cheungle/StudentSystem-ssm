package org.interception;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.common.BaseContext;
import org.common.JWTContent;
import org.config.SpringMVCConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.util.JWTUtil;

import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class LoginInterception implements HandlerInterceptor {
    @Autowired
    public JWTUtil jwtUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        // 获取请求 URI
        String requestURI = request.getRequestURI();
        String contextPath = request.getContextPath();
        String uri = requestURI.substring(contextPath.length());
        response.setContentType("text/html;charset=UTF-8");
        // 放行登录请求
        if (uri.contains("login")) {
            return true;
        }
        log.info("当前请求：", requestURI);
        //1、从请求头中获取令牌
        String token = request.getHeader(jwtUtil.getTokenName());

        //2、校验令牌
        log.info("jwt校验:{}", token);
        // 验证令牌合法性
        if (!JWTUtil.validateToken(jwtUtil.getSecretKey(), token)) {
            log.info("jwt令牌不合法");
            response.setStatus(401);
            return false;
        }
        Claims claims = JWTUtil.parseJWT(jwtUtil.getSecretKey(), token);
        int userId = Integer.parseInt(claims.get(JWTContent.USER_ID).toString());
//        int role = Integer.parseInt(claims.get(JWTContent.ROLE).toString());
        log.info("当前用户id：", userId);

        // 将用户id存储到ThreadLocal
        BaseContext.setCurrentId(userId);
        //3、通过，放行
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }
}
