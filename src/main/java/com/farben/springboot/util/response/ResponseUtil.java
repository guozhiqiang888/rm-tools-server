package com.farben.springboot.util.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ResponseUtil {

    private static String MSG_TITLE = "msg";

    public static Response success(Object obj) {
        return ResponseUtil.success(ResponseCode.SUCCESS.getCode(), obj);
    }

    public static Response success(String statusCode, Object obj) {
        return ResponseUtil.success(statusCode, null, obj);
    }

    /**
     * successful response method (only one msg)
     *
     * @param statusCode statusCode
     * @param code       code
     * @param msg        message
     * @param obj        information of body
     * @return Response
     */
    public static Response success(String statusCode, String code, String msg, Object obj) {
        List<Map<String, String>> reason = new ArrayList<>();
        Map<String, String> msgMap = new HashMap<>(1);
        msgMap.put(ResponseUtil.MSG_TITLE, msg);
        reason.add(msgMap);
        return ResponseUtil.success(statusCode, code, reason, obj);
    }

    public static Response success(String statusCode, String code, List<Map<String, String>> reason, Object obj) {
        Error error = new Error();
        error.setCode(code);
        error.setReason(reason);
        return ResponseUtil.success(statusCode, error, obj);
    }

    public static Response success(String statusCode, Error error, Object obj) {
        Response result = new Response();
        result.setStatusCode(statusCode);
        result.setBody(obj);
        result.setError(error);
        return result;
    }

    /**
     * error info for response method (only one msg)
     *
     * @param statusCode statusCode
     * @param code       code
     * @param msg        message
     * @param obj        information of body
     * @return Response
     */
    public static Response error(String statusCode, String code, String msg, Object obj) {
        return ResponseUtil.success(statusCode, code, msg, obj);

    }
}
