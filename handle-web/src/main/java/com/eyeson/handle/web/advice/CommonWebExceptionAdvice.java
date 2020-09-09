package com.eyeson.handle.web.advice;

import com.eyeson.handle.web.common.RestResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @decription:
 * @author: haytt.xiang
 * @date: 2020/9/9
 * @version:  1.0
*/
@RestControllerAdvice
public class CommonWebExceptionAdvice {

    @ExceptionHandler(Exception.class)
    public RestResult onException(Exception e){
        return new RestResult(500, e.getMessage(),null);
    }

}
