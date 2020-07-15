package com.farben.readme.exception.handle;

import com.farben.readme.exception.CallApiException;
import com.farben.readme.exception.GlobalException;
import com.farben.readme.exception.TokenException;
import com.farben.readme.util.response.Response;
import com.farben.readme.util.response.ResponseCode;
import com.farben.readme.util.response.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    private static Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    public Response exceptionHandler(HttpServletRequest request, Exception e) {
        String code = ResponseCode.ERROR_REQUEST.getCode();
        String msg = ResponseCode.ERROR_REQUEST.getMsg();
        if (e instanceof CallApiException) {
            CallApiException globalException = (CallApiException) e;
            logger.error("CallApiException ,exceptionString:{} ", e.toString());
            ResponseCode responseCode = globalException.getResponseCode();
            if (responseCode == null) {
                code = globalException.getCode();
                msg = globalException.getMessage();
            } else {
                code = responseCode.getCode();
                msg = responseCode.getMsg();
            }

        } else if (e instanceof GlobalException) {
            logger.error("--------system exception----{},", e.getMessage());
            GlobalException globalException = (GlobalException) e;
            ResponseCode responseCode = globalException.getResponseCode();
            code = responseCode.getCode();
            msg = responseCode.getMsg();
        } else if (e instanceof TokenException) {
            logger.error("--------token exception----{},", e.getMessage());
            code = ResponseCode.AUTH_TOKEN_ERROR.getCode();
            msg = e.getMessage();
        } else {
            logger.error(e.getMessage(), e);
        }

        return ResponseUtil.error(code, msg);
    }
}
