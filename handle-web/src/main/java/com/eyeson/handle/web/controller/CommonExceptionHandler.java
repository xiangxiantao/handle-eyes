package com.eyeson.handle.web.controller;

import com.eyeson.handle.web.config.RestResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CommonExceptionHandler {

    @ExceptionHandler(Exception.class)
    public RestResult onException(Exception e){
        return new RestResult(500, e.getMessage(),null);
    }

}
