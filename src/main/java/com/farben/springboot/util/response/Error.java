package com.farben.springboot.util.response;

import java.util.List;
import java.util.Map;

public class Error {

    /*自定义错误码*/
    private String code;
    /*错误原因*/
    private List<Map<String,String>> reason;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Map<String, String>> getReason() {
        return reason;
    }

    public void setReason(List<Map<String, String>> reason) {
        this.reason = reason;
    }
}
