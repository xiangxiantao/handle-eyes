package com.eyeson.handle.web.exception;

import lombok.Data;

@Data
public class EyesException extends RuntimeException {

    private Integer code;

    public EyesException(Integer code, String msg){
        super(msg);
        this.code=code;
    }

}
