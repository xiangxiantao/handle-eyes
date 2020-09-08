package com.eyeson.handle.web.advice;

import com.eyeson.handle.web.common.RestResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonWebExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public RestResult onException(Exception e){
        return new RestResult(500, e.getMessage(),null);
    }

}
