package com.curry.like.hot.hot_service;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hot")
public class TestController {
    @RequestMapping("")
    public String hot(){
        return "hot服务";
    }
}
