package com.farben.springboot.util.response;

public class Response<T> {
    /*错误码*/
    private String statusCode;
    /*提示信息*/
    private Error error;
    /*具体的内容*/
    private T body;

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Error getError() {
        return error;
    }

    public void setError(Error error) {
        if (error != null) {
            this.error = error;
        }
    }

    public T getBody() {
        return body;
    }

    public void setBody(T body) {
        this.body = body;
    }

}
