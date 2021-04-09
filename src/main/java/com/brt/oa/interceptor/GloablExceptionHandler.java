package com.brt.oa.interceptor;

import com.brt.oa.utils.ApiResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @date 2020-04-07
 */

@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(Exception.class)
    public ApiResult handleException(Exception e) {
        String msg = e.getMessage();
        if (msg.equals("401")) {
            return ApiResult.error(401,"未登录");
        }
        return ApiResult.error(msg);
    }
}
