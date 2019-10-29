package com.farben.springboot.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class IndexViewConfig extends WebMvcConfigurerAdapter {
    //记录器
    private Logger logger = LoggerFactory.getLogger(getClass());
    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        System.out.println("=======> system out println ...");

        //日志的级别；
        //由低到高   trace<debug<info<warn<error
        //可以调整输出的日志级别；日志就只会在这个级别以以后的高级别生效
        logger.trace("======> trace log...");
        logger.debug("======> debug log...");
        //SpringBoot默认给我们使用的是info级别的，没有指定级别的就用SpringBoot默认规定的级别；root级别
        logger.info("======> info log...");
        logger.warn("======> warn log...");
        logger.error("======> erro log...");
        registry.addViewController("/").setViewName("forward:/index.html");
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }
}
