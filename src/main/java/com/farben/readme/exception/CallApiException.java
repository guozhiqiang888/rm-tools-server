package com.farben.readme.exception;


import com.farben.readme.util.response.ResponseCode;

public class CallApiException extends GlobalException {
    private String code;
    public CallApiException() {
        super(ResponseCode.CALL_API_ERROR);
    }

    public CallApiException(String code, String msg) {
        super(msg);
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String toString() {
        return "CallProsepectApiException{" +
                "code='" + code + '\'' +
                ",message='" + getMessage() + '\'' +
                '}';
    }
}
