package com.farben.readme;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * main class
 *
 * @author guozhiqiang
 * @date 2019/09/24
 */
//scan the package of 'com.farben.readme.mapper'
//@MapperScan(value = "com.farben.readme.mapper")

@SpringBootApplication(exclude = {MongoAutoConfiguration.class, MongoDataAutoConfiguration.class})
//@EnableAutoConfiguration @ComponentScan
public class EroadApplication extends SpringBootServletInitializer {

	private final static Logger logger = LoggerFactory.getLogger(EroadApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(EroadApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		logger.info("application=================>start");
		System.setProperty("com.ibm.mq.cfg.useIBMCipherMappings", "false");
		logger.info("application=================>System.setProperty(\"com.ibm.mq.cfg.useIBMCipherMappings\", \"false\");");
		logger.info("application=================>end");
		return application.sources(EroadApplication.class);
	}

}
