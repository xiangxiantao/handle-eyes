package com.eyeson.handle.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * 将swagger文档设置为首页
 */
@Controller("indexController")
@ApiIgnore
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/swagger-ui.html";
    }

}
