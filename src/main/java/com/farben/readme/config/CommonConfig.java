/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.config;

import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;

/**
 * @ClassName: CommonConfig
 * @Description: TODO
 * @author: 43994701
 * @date May 21, 2020
 */
@Configuration
public class CommonConfig {

    @Autowired
    private JwtConfig jwtConfig;

    @Bean
    public SecretKeySpec loadJWTSecretKey() {
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(jwtConfig.getSecret());
        return new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());
    }
}
