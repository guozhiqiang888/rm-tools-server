package com.farben.readme.interceptor;

import com.alibaba.druid.util.StringUtils;
import com.farben.readme.annotation.Auth;
import com.farben.readme.constant.Constant;
import com.farben.readme.exception.TokenException;
import com.farben.readme.service.ITokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AuthValidInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private ITokenService tokenService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //validate sign
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
            if (auth != null) {
                String token = request.getHeader(Constant.HEADER_TOKNE);
                if (StringUtils.isEmpty(token)) {
                    throw new TokenException("Empty token in the header");
                }
                boolean result = this.tokenService.validateToken(token);
                if (!result) {
                    throw new TokenException("Invalid token, please check.");
                }
//                    throw new GlobalException(ResponseCode.AUTH_TOKEN_ERROR);
            }
        }
        return super.preHandle(request, response, handler);
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        super.postHandle(request, response, handler, modelAndView);
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        super.afterCompletion(request, response, handler, ex);
    }

    @Override
    public void afterConcurrentHandlingStarted(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        super.afterConcurrentHandlingStarted(request, response, handler);
    }
}
