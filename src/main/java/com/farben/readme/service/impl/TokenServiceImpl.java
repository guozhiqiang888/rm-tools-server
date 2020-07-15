/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.service.impl;

import com.farben.readme.constant.Constant;
import com.farben.readme.service.ITokenService;
import com.farben.readme.util.TimeUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.impl.DefaultClaims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: TokenServiceImpl
 * @Description: TODO
 * @author: 43994701
 * @date May 21, 2020
 */

@Service
public class TokenServiceImpl implements ITokenService {

    private final static Logger log = LoggerFactory.getLogger(TokenServiceImpl.class);

    @Autowired
    private SecretKeySpec key;

    @Override
    public String createToken(final Map<String, Object> paraMap) {
        log.info("Start to generate token with the param [{}] ", paraMap);
        // set expiration time
        Calendar notTime = Calendar.getInstance();
        notTime.add(Calendar.MINUTE, Constant.TOKEN_EXPIRATION_MINUTES);
        Date expireDate = notTime.getTime();

        // set JWT header info (contain algorithm and type)
        Map<String, Object> headerClaims = new HashMap<String, Object>();
        headerClaims.put("alg", SignatureAlgorithm.HS512.getJcaName());
        headerClaims.put("typ", "JWT");

        // custom section
        Claims claims = new DefaultClaims(paraMap);
        JwtBuilder builder = Jwts.builder().setHeader(headerClaims).setClaims(claims).setExpiration(expireDate).signWith(SignatureAlgorithm.HS512, this.key);
        String token = builder.compact();
        log.info("Successfully generate token [{}]  with the param [{}] , and the expire date is [{}] ", token, paraMap, TimeUtil.getTime(expireDate.getTime()));
        return token;
    }

    @Override
    public String createNotExpiredToken(final Map<String, Object> paraMap, final String key) {
        log.info("Start to generate not expired token with the param [{}] ", paraMap);

        // set JWT header info (contain algorithm and type)
        Map<String, Object> headerClaims = new HashMap<String, Object>();
        headerClaims.put("alg", SignatureAlgorithm.HS512.getJcaName());
        headerClaims.put("typ", "JWT");

        // create Secret Key
        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
        SecretKeySpec secretKeySpec = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

        // custom section
        Claims claims = new DefaultClaims(paraMap);
        JwtBuilder builder = Jwts.builder().setHeader(headerClaims).setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKeySpec);
        String token = builder.compact();
        log.info("Successfully generate not expired token [{}]  with the param [{}] , and it's not expired. ", token, paraMap);
        return token;
    }

    @Override
    public boolean validateToken(final String token) {
        Claims claims = parseToken(token);
        return doValidateToken(claims, token);
    }

    @Override
    public Claims parseToken(final String token) {
        log.info("Start to parse token [{}] ", token);
        Claims result = null;
        try {
            result = Jwts.parser().setSigningKey(this.key).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("Failed  to parse token [{}] ", token);
            log.error(Constant.TOKEN_PARSE_FAILED, token, e);
        }
        log.info("End to parse token [{}] ", token);
        return result;
    }


    @Override
    public Claims parseNotExpiredToken(final String token, final String key) {
        log.info("Start to parse not expired token [{}] ", token);
        Claims result = null;
        try {
            // create Secret Key
            byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(key);
            SecretKeySpec secretKeySpec = new SecretKeySpec(apiKeySecretBytes, SignatureAlgorithm.HS512.getJcaName());

            result = Jwts.parser().setSigningKey(secretKeySpec).parseClaimsJws(token).getBody();
        } catch (Exception e) {
            log.info("Failed  to parse not expired token [{}] ", token);
            log.error(Constant.TOKEN_PARSE_FAILED, token, e);
        }
        log.info("End to parse not expired token [{}] ", token);
        return result;
    }

    private boolean doValidateToken(final Claims claims, final String token) {
        if (claims == null) {
            return false;
        }
        Date expirationDate = claims.getExpiration();
        log.info("The expiration date of token [{}] is [{}]", token, TimeUtil.getTime(expirationDate.getTime()));
        // expiration date is before current date time , this token has been expired.
        if (expirationDate.before(new Date())) {
            log.info("The token has been expired , the expired time is [{}]", token, TimeUtil.getTime(expirationDate.getTime()));
            return false;
        }
        return true;
    }
}
