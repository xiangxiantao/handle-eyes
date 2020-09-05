package com.eyeson.handle.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MainController {

    @GetMapping("/welcome")
    public String welcome(@RequestParam("user") String user){
        if ("-1".equals(user)){
            throw new RuntimeException("welcome错误");
        }
        return "welcome";
    }

}
