package com.farben.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class IndexViewConfig extends WebMvcConfigurerAdapter {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        logger.info("==========> forward to index.html==========>start");
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
        logger.info("==========> forward to index.html==========>end");
    }
}
