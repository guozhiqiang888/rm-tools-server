/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2018. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.bean.logon;

import java.io.Serializable;

/**
 * <p>
 * <b> Wrap a ldap user </b>
 * </p>
 */
public class LogonResponse implements Serializable {

    /**
     * Given name
     */
    private String givenName;

    /**
     * Last name
     */
    private String lastName;

    /**
     * E-mail
     */
    private String email;

    /**
     * member of
     */
    private String memberOf;

    private String token;

    private String reacquireToken;

    private AuthenError authenError;

    /**
     * @return the givenName
     */
    public String getGivenName() {
        return this.givenName;
    }

    /**
     * @param givenName the givenName to set
     */
    public void setGivenName(final String givenName) {
        this.givenName = givenName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return this.lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(final String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return this.email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(final String email) {
        this.email = email;
    }

    /**
     * @return the memberOf
     */
    public String getMemberOf() {
        return this.memberOf;
    }

    /**
     * @param memberOf the memberOf to set
     */
    public void setMemberOf(final String memberOf) {
        this.memberOf = memberOf;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReacquireToken() {
        return reacquireToken;
    }

    public void setReacquireToken(String reacquireToken) {
        this.reacquireToken = reacquireToken;
    }

    public AuthenError getAuthenError() {
        return authenError;
    }

    public void setAuthenError(AuthenError authenError) {
        this.authenError = authenError;
    }

    @Override
    public String toString() {
        return "LogonResponse{" +
                "givenName='" + givenName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", memberOf='" + memberOf + '\'' +
                ", token='" + token + '\'' +
                ", reacquireToken='" + reacquireToken + '\'' +
                ", authenError=" + authenError +
                '}';
    }
}
