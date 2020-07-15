/*
 * COPYRIGHT. HSBC HOLDINGS PLC 2017. ALL RIGHTS RESERVED.
 *
 * This software is only to be used for the purpose for which it has been
 * provided. No part of it is to be reproduced, disassembled, transmitted,
 * stored in a retrieval system nor translated in any human or computer
 * language in any way or for any other purposes whatsoever without the prior
 * written consent of HSBC Holdings plc.
 */
package com.farben.readme.constant;

public class Constant {

    private Constant() {
        throw new IllegalAccessError("Constant Class");
    }

    public static final String TRUST_STORE = "trustStore";
    public static final String CA_PATH = "caPath";
    public static final String CA_PWD = "caPwd";
    public static final String LDAP_MEMBER_OF = "memberOf";
    public static final String LDAP_MAIL = "mail";
    public static final String LDAP_GIVEN_NAME = "hsbc-ad-KnownAsGivenName";
    public static final String LDAP_LAST_NAME = "hsbc-ad-KnownAsLastName";
    public static final String SSL_SOCKETFAC_PROVIDER = "ssl.SocketFac.prov";
    public static final String IBM_SSLIMP = "ibm.SSLSocketFacImpl";
    public static final String SSL_SERVERSOCKETFAC_PROVIDER = "ssl.SerSocketFac.prov";
    public static final String IBM_SSLSERVERIMP = "ibm.SSLSerSocketFacImpl";

    public static final String IS_INCLUDE_RICH_TEXT = "isIncludeRichText";
    public static final String EXCLUDES = "excludes";

    public static final String STATUS_FALSE = "0";
    public static final String STATUS_TRUE = "1";

    public static final String USER_AUTH_CACHE_NAME = "authGroups";

    public static final String AUTH_SUCCESS_CODE = "WE_AUTH_000";
    public static final String AUTH_SUCCESS_REASONS = "Success";

    public static final String AUTH_SYS_CODE = "WE_AUTH_001";
    public static final String AUTH_SYS_REASONS = "INTERNAL_SYSTEM_ERROR";

    public static final int TOKEN_EXPIRATION_MINUTES = 10 * 1;
    public static final int TOKEN_TIMESTAMP_LAST_TIME = 5 * 60 * 1000;

    public static final String TOKEN_PARSE_FAILED = "Failed to parse this token [{}]- and the detail is [{}]";

    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS_SSS = "yyyy-MM-dd HH:mm:ss:sss";
    public static final String PATTERN_YYYY_MM_DD_HH_MM_SS = "yyyy-MM-dd HH:mm:ss";
    public static final String PATTERN_YYYY_MM_DD = "yyyy-MM-dd";
    public static final String PATTERN_YYYY_MMM_DD_HH_MM_SS_ZZZ = "yyyy-MMM-dd HH:mm:ss zzz";
    public static final String PATTERN_YYYY_MMM_DD_HH_MM_SSZ = "yyyy-MMM-dd HH:mm:ssZ";

    public static final String HYPHEN = "-";
    public static final String TOKEN_SIGNATURE = "Eroad!@#$369";
    public static final String AES_128_KEY = "nhldiNtza2EWhKj93mHUvA==";

    public static final String CLIENT_ID_KEY = "client_id";
    public static final String CLIENT_SECRET_KEY = "client_secret";
    public static final String ENCRYPT_EMAIL_PARAMETER = "encryptEmail";

    public static final String HEADER_TOKNE = "token";
    public static final String HEADER_REQUEST_ID = "requestId";
    public static final String HEADER_REACQUIRE_TOKEN = "reacquireToken";

    public final static String STAFF_ID_KEY = "staffId";
    public final static String STAFF_NAME_KEY = "staffName";
    public final static String STAFF_PWD_KEY = "staffPwd";

    public final static String IS_PUBLISHED_YES = "Y";

}
