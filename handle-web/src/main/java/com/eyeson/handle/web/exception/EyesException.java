package com.eyeson.handle.web.exception;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @decription:
 * @author: haytt.xiang
 * @date: 2020/9/9
 * @version:  1.0
*/
@EqualsAndHashCode(callSuper = true)
@Data
public class EyesException extends RuntimeException {

    private Integer code;

    public EyesException(Integer code, String msg){
        super(msg);
        this.code=code;
    }

}
