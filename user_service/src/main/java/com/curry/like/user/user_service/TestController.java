package com.curry.like.user.user_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class TestController {

    @RequestMapping("")
    public String user(){
        return "user服务";
    }
}
