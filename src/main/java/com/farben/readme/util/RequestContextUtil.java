package com.farben.readme.util;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;

public class RequestContextUtil {

    public static final String REQUEST_ID = "requestId";

    public static final String STAFF_ID_KEY = "staffId";

    public static final String PERSON_ID_KEY = "personId";

    public static final String OPEN_ID_KEY = "openId";

    public static HttpServletRequest getRequest() {
        return getRequestAttributes().getRequest();
    }

    public static String getRequestId() {
        return XssCleanUtil.cleanXss(getRequest().getHeader(REQUEST_ID));
    }

    public static String getOpenId() {
        return XssCleanUtil.cleanXss(getRequest().getHeader(OPEN_ID_KEY));
    }

    public static String getStaffId() {
        return XssCleanUtil.cleanXss(getRequest().getHeader(STAFF_ID_KEY));
    }

    public static String getPersonId() {
        return XssCleanUtil.cleanXss(getRequest().getHeader(PERSON_ID_KEY));
    }

    public static ServletRequestAttributes getRequestAttributes() {
        return (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    }

    public static ServletContext getServletContext() {
        return ContextLoader.getCurrentWebApplicationContext().getServletContext();
    }
}
