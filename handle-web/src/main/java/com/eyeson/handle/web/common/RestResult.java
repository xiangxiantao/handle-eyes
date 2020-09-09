package com.eyeson.handle.web.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @decription: Rest风格接口的统一响应格式
 * @author: haytt.xiang
 * @date: 2020/9/9
 * @version:  1.0
*/
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RestResult<T> {

    private static final Integer SUCCESS = 200;

    private Integer code;

    private String msg;

    private T data;

    public static RestResult ok(){
        return new RestResult(SUCCESS,null,null);
    }

    public static <D> RestResult ok(D data){
        return new RestResult(SUCCESS, null, data);
    }

    //todo 定义异常信息枚举，利用枚举构造失败的响应

}
