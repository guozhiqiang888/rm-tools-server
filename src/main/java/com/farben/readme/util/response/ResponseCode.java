package com.farben.readme.util.response;

public enum ResponseCode {
    SUCCESS("200", "success"),
    ERROR_REQUEST("400", "error request"),
    CALL_WECHAT_ERROR("400-0001", "call wechat error"),
    CALL_API_ERROR("400-0002", "call api error"),
    ERROR_INPUT("400-0003", "error input"),
    COMMON_ERROR("400-0004", "Common error"),
    INSERT_ERROR("400-0005", "Insert data error"),
    IS_MEMBER_OF_ERROR("400-0006", "ldap group error"),
    LDAP_COMMON_ERROR("400-0007", "ldap common error"),
    CALL_HSBC_API_ERROR("400-0008", "call hsbc api error"),
    PUBLISH_ERROR("400-0009", "Publish data error"),
    AUTH_TOKEN_ERROR("401-0001", "Un logon"),
    TENCENT_LOGON_ERROR("401-0002", "Tencent's staffId and password are wrong!"),
    INTERNAL_ERROR("500", "internal error"),
    NOT_FOUND("404", "Not Found"),

    LDAP_STAFF_OR_PWD_ERROR("400-A002", "Staff ID or password is incorrect, please input the correct staff ID and password"),
    LDAP_ACCOUNT_LOCKED_ERROR("400-A003", "account Locked"),
    LDAP_ACCOUNT_DISABLED_ERROR("400-A004", "account disabled"),
    LDAP_ACCOUNT_EXPIRED_ERROR("400-A005", "account expired"),
    LDAP_PWD_EXPIRED_ERROR("400-A006", "password expired"),
    LDAP_USERNAME_NOT_FOUND_ERROR("400-A007", "username not found"),

    REACQUIRE_TOKEN_PARSE_ERROR("400-1001", "reacquire token parse error"),
    STAFF_CACHE_ERROR("400-1002", "staff cache is null"),

    FILE_NOT_SELECT_ERROR("400-1003", "not select file"),
    FILE_IS_EXIST_ERROR("400-1004", "file is exist"),
    FILE_UPLOAD_ERROR("400-1005", "upload file failed"),
    FILE_NOT_EXIST_ERROR("400-1006", "file is not exist"),
    FILE_DOWNLOAD_ERROR("400-1007", "file download failed");

    private String code;
    private String msg;

    private ResponseCode(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public static String getMsg(String code) {
        for (ResponseCode rc : ResponseCode.values()) {
            if (rc.getCode().equals(code)) {
                return rc.msg;
            }
        }
        return null;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
