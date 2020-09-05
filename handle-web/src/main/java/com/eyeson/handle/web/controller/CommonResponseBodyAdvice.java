package com.eyeson.handle.web.controller;

import com.eyeson.handle.web.config.RestResult;
import com.google.gson.Gson;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice
public class CommonResponseBodyAdvice implements ResponseBodyAdvice {

    private static Gson gson = new Gson();

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //拦截所有接口
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //如果已经是RestResult，表示已经被ExceptionHandler切面捕获到了异常，直接返回即可
        if (o instanceof RestResult) {
            return o;
        }
        //对于返回值类型为String的Controller方法，需要做特殊的处理
        //对于字符串的ContentType是“text-plain”，ConverterType是StringHttpMessageConverter这个类型转换，由于将结果封装成了自定义的Result类型，所以有Result转换成String报错。
        //解决方案1.对String类型的返回值类型进行特殊处理。将该方法返回为json字符串
        //解决方案2.增加一个全局的gsonHttpMessageConverter，并置顶，用于处理自定义类型
        //RestResult restResult;
        //if (o instanceof String){
        //    return gson.toJson(RestResult.ok(o));
        //}
        //其余的类型直接封装为restResult响应输出
        return RestResult.ok(o);
    }


}
