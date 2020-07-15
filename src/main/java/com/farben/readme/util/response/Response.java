package com.farben.readme.util.response;

public class Response<T> {
    /*error code*/
    private String code;
    /**
     * tip info
     **/
    private String msg;

    private String times;

    private String requestId;

    /*content*/
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
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

    public String getTimes() {
        return times;
    }

    public void setTimes(String times) {
        this.times = times;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    @Override
    public String toString() {
        return "Response{" +
                "code='" + code + '\'' +
                ", msg='" + msg + '\'' +
                ", times='" + times + '\'' +
                ", requestId='" + requestId + '\'' +
                ", data=" + data +
                '}';
    }
}
