package com.farben.readme.config;

import com.farben.readme.interceptor.AuthValidInterceptor;
import com.farben.readme.interceptor.EditAuthInterceptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public AuthValidInterceptor getAuthValidInterceptor() {
        return new AuthValidInterceptor();
    }

    @Bean
    public EditAuthInterceptor getEditAuthInterceptor() {
        return new EditAuthInterceptor();
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedHeaders("*")
                .allowedMethods("*")
                .allowedOrigins("*")
                .allowCredentials(true);
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(getAuthValidInterceptor()).addPathPatterns("/**");
        registry.addInterceptor(getEditAuthInterceptor()).addPathPatterns("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("==========> forward to backend index.html==========>start");
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        logger.info("==========> forward to backend index.html==========>end");
    }
}
