package com.farben.readme.util.response;


import com.farben.readme.util.RequestContextUtil;

import java.text.SimpleDateFormat;
import java.util.*;

public class ResponseUtil {

    public static Response success(){
        return ResponseUtil.success(null);
    }

    public static Response success(Object obj) {
        return ResponseUtil.success(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), obj);
    }

    public static Response success(String code, String msg, Object obj) {
        Response result = new Response();
        result.setCode(code);
        result.setMsg(msg);
        result.setData(obj);
        result.setTimes(getTime());
        result.setRequestId(RequestContextUtil.getRequestId());
        return result;
    }

    /**
     * error info for response method (only one msg)
     *
     * @param code       code
     * @param msg        message
     * @return Response
     */
    public static Response error(String code, String msg) {
        Response result = new Response();
        result.setCode(code);
        result.setMsg(msg);
        result.setTimes(getTime());
        result.setRequestId(RequestContextUtil.getRequestId());
        return result;

    }

    public static Response error(ResponseCode responseCode) {
        return error(responseCode.getCode(), responseCode.getMsg());

    }

    private static String getTime(){
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return simpleDateFormat.format(new Date());

    }
}
