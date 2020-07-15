package com.farben.readme.exception;


import com.farben.readme.util.response.ResponseCode;

public class GlobalException extends RuntimeException{

    private ResponseCode responseCode;

    public GlobalException(String msg){
        super(msg);
    }

    public GlobalException(ResponseCode responseCode){
        super(responseCode.getMsg());
        this.responseCode = responseCode;
    }

    public ResponseCode getResponseCode() {
        return responseCode;
    }

    public void setResponseCode(ResponseCode responseCode) {
        this.responseCode = responseCode;
    }
}
