package com.eyeson.handle.web.advice;

import com.eyeson.handle.web.common.RestResult;
import com.google.gson.Gson;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * RESTful接口全局统一响应格式切面
 * 这个切面需要比较精细化的指定受控制的包，不能将swagger的配置类等纳入管理，
 * 否则将导致swagger的输出内容格式变化，最终导致swagger不能正常工作
 */
@RestControllerAdvice(basePackages = "com.eyeson.handle.web.controller")
public class CommonWebResponseBodyAdvice implements ResponseBodyAdvice {

    /**
     * 自定义需要拦截的接口，这里默认拦截所有接口，也可以根据业务需求进行判断
     * @return
     */
    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        //拦截所有接口
        return true;
    }

    /**
     * 这种全局统一响应格式的方法需要搭配GsonHttpMessageConverter用于处理所有类型的返回值
     * 如果没有json相关的HttpMessageConverter，String类型返回值的Controller方法将交给StringHttpMessageConverter处理，
     * 而该切面将返回值类型改为了RestResult，这将会导致类型转换错误
     */
    @Override
    public Object beforeBodyWrite(Object o, MethodParameter methodParameter, MediaType mediaType, Class aClass, ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        //如果已经是RestResult，表示已经被ExceptionHandler切面捕获到了异常，直接返回即可
        if (o instanceof RestResult) {
            return o;
        }
        //其余的类型直接封装为restResult响应输出
        return RestResult.ok(o);
    }


}
