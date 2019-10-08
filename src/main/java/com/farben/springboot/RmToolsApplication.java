package com.farben.springboot;

//import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
/**
 * Demo class
 *
 * @author guozhiqiang
 * @date 2019/09/24
 */
//扫描com.farben.springboot.mapper包
//@MapperScan(value = "com.farben.springboot.mapper")
@SpringBootApplication
public class RmToolsApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(RmToolsApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(RmToolsApplication.class);
	}

}
