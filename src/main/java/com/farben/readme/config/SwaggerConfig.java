/*
 *
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 *
 */

package com.farben.readme.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ApiKey;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @ClassName: SwaggerConfiguration
 * @Description: the settings of swagger ui
 * @author: 43994701
 * @date May 18, 2020
 *
 *       note that the swagger-ui is just enable in the dev env
 *
 */
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    private static final String CONTROLLER_PATH = "com.farben.readme.controller";
    private static final String SWAGGER_TITLE = "China CMB WeChat Admin Console Application";
    private static final String SWAGGER_DESCRIPTION = "this is the document for the wechat-readme's API";
    private static final String CONTACT_POINT = "zhi.qiang.guo_sp@noexternalmail.hsbc.com";
//    private static final String CONTACT_URL = "https://alm-confluence.systems.uk.hsbc/confluence/display/PRD/Project+RM+Tool";
    private static final String CONTACT_URL = "";
    private static final String VERSION = "v1.0.0";
    private static final String SECURITY_HEADER_NAME = "X-HSBC-Saml";

    @Bean
    public Docket api() {
        return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.basePackage(SwaggerConfig.CONTROLLER_PATH)).paths(PathSelectors.any()).build()
                .securitySchemes(apiKey()).apiInfo(apiInfo());
    }

    private List<ApiKey> apiKey() {
        List<ApiKey> apiKeys = new ArrayList<ApiKey>();
        ApiKey samlToken = new ApiKey("saml_token", SwaggerConfig.SECURITY_HEADER_NAME, "header");
        apiKeys.add(samlToken);
        return apiKeys;
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder().title(SwaggerConfig.SWAGGER_TITLE).description(SwaggerConfig.SWAGGER_DESCRIPTION).version(SwaggerConfig.VERSION)
                .contact(new Contact(SwaggerConfig.SWAGGER_TITLE, SwaggerConfig.CONTACT_URL, SwaggerConfig.CONTACT_POINT)).build();
    }

}
