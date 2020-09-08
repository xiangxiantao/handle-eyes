package com.eyeson.handle.web.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Api(value = "基础服务")
@RestController
public class MainController {

    @ApiOperation(value = "欢迎信息")
    @GetMapping("/welcome")
    public String welcome(@RequestParam("user") String user){
        if ("-1".equals(user)){
            throw new RuntimeException("welcome错误");
        }
        return "welcome";
    }

    @ApiOperation(value = "时间-time")
    @GetMapping("/time")
    public Date now(){
        return new Date();
    }

    @ApiOperation(value = "时间-localDate")
    @GetMapping("/localDate")
    public LocalDate localDate(){
        return LocalDate.now();
    }

    @ApiOperation(value = "时间-localDateTime")
    @GetMapping("/localDateTime")
    public LocalDateTime localDateTime(){
        return LocalDateTime.now();
    }
}
