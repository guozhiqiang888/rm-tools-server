package com.farben.readme.filter;

import com.farben.readme.constant.Constant;
import org.apache.commons.lang.BooleanUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XssFilter implements Filter {
    private static Logger logger = LoggerFactory.getLogger(XssFilter.class);

    // is filter rich text
    private static boolean IS_INCLUDE_RICH_TEXT = false;

    private static String SPLIT = ",";

    public List<String> excludes = new ArrayList<>();

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("=====================>xss filter is open");
        }
        HttpServletRequest req = (HttpServletRequest) request;
        if (handleExcludeURL(req)) {
            filterChain.doFilter(request, response);
            return;
        }
        XssHttpServletRequestWrapper xssRequest = new XssHttpServletRequestWrapper(req, IS_INCLUDE_RICH_TEXT);
        filterChain.doFilter(xssRequest, response);
    }

    private boolean handleExcludeURL(HttpServletRequest request) {
        if (excludes == null || excludes.isEmpty()) {
            return false;
        }
        String url = request.getServletPath();
        for (String pattern : excludes) {
            if (url.contains(pattern)) {
                return true;
            }
//            Pattern p = Pattern.compile("^" + pattern);
//            Matcher m = p.matcher(url);
//            if (m.find()) {
//                return true;
//            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        if (logger.isDebugEnabled()) {
            logger.debug("=====================>xss filter init");
        }
        String isIncludeRichText = filterConfig.getInitParameter(Constant.IS_INCLUDE_RICH_TEXT);
        if (StringUtils.isNotEmpty(isIncludeRichText)) {
            IS_INCLUDE_RICH_TEXT = BooleanUtils.toBoolean(isIncludeRichText);
        }
        String temp = filterConfig.getInitParameter(Constant.EXCLUDES);
        if (temp != null) {
            String[] url = temp.split(SPLIT);
            for (int i = 0; url != null && i < url.length; i++) {
                excludes.add(url[i]);
            }
        }
    }

    @Override
    public void destroy() {
    }

}
