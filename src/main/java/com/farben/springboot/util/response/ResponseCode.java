package com.farben.springboot.util.response;

public enum     ResponseCode {
    SUCCESS("200","success"),
    ERROR_REQUEST("400","error request");

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
