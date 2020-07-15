package com.farben.readme.interceptor;

import com.farben.readme.annotation.EditAuth;
import com.farben.readme.bean.logon.LogonResponse;
import com.farben.readme.config.EroadConstantsConfig;
import com.farben.readme.constant.Constant;
import com.farben.readme.exception.GlobalException;
import com.farben.readme.service.ICacheService;
import com.farben.readme.service.ILoginService;
import com.farben.readme.util.response.ResponseCode;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class EditAuthInterceptor extends HandlerInterceptorAdapter {

    private final static Logger logger = LoggerFactory.getLogger(EditAuthInterceptor.class);

    @Autowired
    private ICacheService cacheService;

    @Autowired
    private EroadConstantsConfig eroadConstantsConfig;

    @Autowired
    private ILoginService loginService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //validate sign
        if (handler instanceof HandlerMethod) {
            HandlerMethod handlerMethod = (HandlerMethod) handler;
            EditAuth editAuth = handlerMethod.getMethodAnnotation(EditAuth.class);
            if (editAuth != null) {
                List<String> editAuthGroups = eroadConstantsConfig.getEditAuthGroups();
                String token = request.getHeader(Constant.HEADER_TOKNE);
                String staffId = loginService.getStaffIdFromLogonToken(token);
                LogonResponse ldapStaff = (LogonResponse) cacheService.get(Constant.USER_AUTH_CACHE_NAME, staffId);
                String memberOf = ldapStaff.getMemberOf();
                logger.info("======>ldapStaff: {}", ldapStaff);
                boolean hasEditAuth = false;
                for (String str : editAuthGroups) {
                    if (memberOf.indexOf(str) != -1) {
                        hasEditAuth = true;
                    }
                }
                if (!hasEditAuth) {
                    throw new GlobalException(ResponseCode.IS_MEMBER_OF_ERROR);
                }
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
