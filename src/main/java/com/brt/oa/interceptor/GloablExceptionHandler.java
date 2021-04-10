package com.brt.oa.interceptor;

import com.brt.oa.utils.ApiResult;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author
 * @date 2021-04-07
 */

@ControllerAdvice
public class GloablExceptionHandler {
    @ResponseBody
    @ExceptionHandler(RuntimeException.class)
    public ApiResult handleRuntimeException(RuntimeException e) {
        String msg = e.getMessage();
        if (msg.equals("401")) {
            return ApiResult.error(401,"未登录");
        }
        return ApiResult.error(msg);
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ApiResult handlerArgumentNotValidException(MethodArgumentNotValidException exception) {
        BindingResult result = exception.getBindingResult();
        FieldError fieldError = result.getFieldError();
        return ApiResult.error(fieldError.getDefaultMessage());
    }

}
