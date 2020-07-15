/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2016. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.service;


import com.farben.readme.exception.TokenException;

/**
 * @ClassName: ILoginService
 * @Description: TODO
 * @author: 43994701
 * @date May 21 , 2020
 */
public interface ILoginService {

    String generateAccessToken(String encryptedRequest) throws TokenException;

    String generateLogonToken(String staffId, String staffName);

    String getStaffIdFromLogonToken(String token);

    String getStaffNameFromLogonToken(String token);

    String generateReacquireToken(String staffId, String staffName, String pwd, String token);

}
