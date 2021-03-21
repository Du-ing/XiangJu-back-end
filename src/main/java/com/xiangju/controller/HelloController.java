package com.xiangju.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping({"index", "/hello", "/"})
    public String hello(){
        return "Hello，后端启动成功！";
    }
}
