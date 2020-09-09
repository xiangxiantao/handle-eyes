package com.eyeson.handle.web.advice;

import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.mvc.method.annotation.RequestBodyAdviceAdapter;

import java.lang.reflect.Type;


/**
 * @decription:
 * 通过该切面可以在httpMessageConverter read request之前，对request的内容进行操作
 * 适用场景：接口签名验证
 * @author: haytt.xiang
 * @date: 2020/9/9
 * @version:  1.0
*/
public class CommonWebRequestBodyAdvice extends RequestBodyAdviceAdapter {




    @Override
    public boolean supports(MethodParameter methodParameter, Type type, Class<? extends HttpMessageConverter<?>> aClass) {
        return false;
    }
}
