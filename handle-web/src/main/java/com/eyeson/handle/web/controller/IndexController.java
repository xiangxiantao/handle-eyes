package com.eyeson.handle.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import springfox.documentation.annotations.ApiIgnore;

/**
 * @decription:  将swagger文档设置为首页
 * @author: haytt.xiang
 * @date: 2020/9/9
 * @version:  1.0
*/
@Controller("indexController")
@ApiIgnore
public class IndexController {

    @RequestMapping(value = "/")
    public String index(){
        return "redirect:/swagger-ui.html";
    }

}
