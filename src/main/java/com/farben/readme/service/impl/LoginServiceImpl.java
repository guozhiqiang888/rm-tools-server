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

import com.alibaba.druid.util.StringUtils;
import com.farben.readme.constant.Constant;
import com.farben.readme.exception.TokenException;
import com.farben.readme.service.ILoginService;
import com.farben.readme.service.ITokenService;
import com.farben.readme.util.TimeUtil;
import com.farben.readme.util.aes.CoreSymmetricCryptographyUtil;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName: LoginServiceImpl
 * @Description: TODO
 * @author: 43994701
 * @date May 21, 2020
 */

@Service
public class LoginServiceImpl implements ILoginService {

    private final static Logger log = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Autowired
    private ITokenService tokenService;

    @Override
    public String generateAccessToken(final String encryptedRequest) throws TokenException {
        String decryptedRequest = decrypt(encryptedRequest);
        return doGenerateAccessToken(decryptedRequest);
    }

    @Override
    public String generateLogonToken(final String staffId, final String staffName) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put(Constant.STAFF_ID_KEY, staffId);
        paraMap.put(Constant.STAFF_NAME_KEY, staffName);
        return tokenService.createToken(paraMap);
    }

    @Override
    public String getStaffIdFromLogonToken(final String token) {
        Claims claims = tokenService.parseToken(token);
        Object o = claims.get(Constant.STAFF_ID_KEY);
        return o == null ? null : o.toString();
    }

    @Override
    public String getStaffNameFromLogonToken(final String token) {
        Claims claims = tokenService.parseToken(token);
        Object o = claims.get(Constant.STAFF_NAME_KEY);
        return o == null ? null : o.toString();
    }

    @Override
    public String generateReacquireToken(String staffId, String staffName, String pwd, String token) {
        Map<String, Object> paraMap = new HashMap<>();
        paraMap.put(Constant.STAFF_ID_KEY, staffId);
        paraMap.put(Constant.STAFF_NAME_KEY, staffName);
        paraMap.put(Constant.STAFF_PWD_KEY, pwd);
        return tokenService.createNotExpiredToken(paraMap, token);
    }

    private String decrypt(final String loginRequest) throws TokenException {
        log.info("Start to decrypt the request [{}]", loginRequest);
        String decryptedRequest = "";
        try {
            decryptedRequest = CoreSymmetricCryptographyUtil.decryptData(Constant.AES_128_KEY, loginRequest);
        } catch (Exception e) {
            log.error("Failed to decrypted the encrypted reques [{}], the detail reason is [{}] ", loginRequest, e);
            throw new TokenException("Failed to decrypt the encrypted reques");
        }
        log.info("Successfully decrypt the request [{}]", decryptedRequest);
        return decryptedRequest;
    }

    /**
     * @param decryptedRequest
     * @return
     * @throws TokenException
     * @Title: doGenerateAccessToken
     * @Description: Generate token by the decrypted request
     * @return: String
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private String doGenerateAccessToken(final String decryptedRequest) throws TokenException {
        String[] content = decryptedRequest.split(Constant.HYPHEN);
        isValidRequest(content[0], content[1]);
        Map request = prepareTokenClaims(content);
        return this.tokenService.createToken(request);
    }

    /**
     * @param content
     * @return
     * @Title: prepareTokenClaims
     * @Description: TODO
     * @return: Map
     */
    @SuppressWarnings({"rawtypes", "unchecked"})
    private Map prepareTokenClaims(final String[] content) {
        Map request = new HashMap();
        request.put("signature", content[0]);
        request.put("timeStamp", content[1]);
        return request;
    }

    private void isValidRequest(final String signature, final String timeStamp) throws TokenException {
        log.info("Start to validate the request");
        if (StringUtils.isEmpty(signature) || StringUtils.isEmpty(timeStamp)) {
            log.error("Invalid signature");
            throw new TokenException("Invalid signature");
        }
        if (!Constant.TOKEN_SIGNATURE.equals(signature)) {
            log.error("Invalid signature");
            throw new TokenException("Invalid signature");
        }
        if (TimeUtil.expiredTimeStamp(timeStamp)) {
            log.error("Expired timestamp");
            throw new TokenException("Expired timeStamp");
        }
        log.info("Successfully validate the request");
    }
}
