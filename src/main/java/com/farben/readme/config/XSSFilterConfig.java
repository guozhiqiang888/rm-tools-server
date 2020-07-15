package com.farben.readme.config;

import com.farben.readme.constant.Constant;
import com.farben.readme.filter.XssFilter;
import com.google.common.collect.Maps;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.Map;

@Configuration
public class XSSFilterConfig {

    /**
     * xss filter
     *
     * @return
     */
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        filterRegistrationBean.setFilter(xssFilter());
        filterRegistrationBean.setOrder(1);
        filterRegistrationBean.setEnabled(true);
        filterRegistrationBean.addUrlPatterns("/*");
        Map<String, String> initParameters = Maps.newHashMap();
//        initParameters.put(Constant.EXCLUDES, "/favicon.ico,/img/*,/js/*,/css/*");
//        initParameters.put(Constant.EXCLUDES, ".js,.gif,.jpg,.png,.css,.ico");
        initParameters.put(Constant.IS_INCLUDE_RICH_TEXT, "true");
        filterRegistrationBean.setInitParameters(initParameters);
        return filterRegistrationBean;
    }

    /**
     * filter bean
     * @return
     */
    @Bean(name = "xssFilter")
    public Filter xssFilter() {
        return new XssFilter();
    }
}
