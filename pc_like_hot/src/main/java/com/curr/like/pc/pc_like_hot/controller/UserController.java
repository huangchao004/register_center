package com.curr.like.pc.pc_like_hot.controller;

import com.curr.like.pc.pc_like_hot.feign.IPcUserService;
import com.curry.like.model.model.response.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private IPcUserService iPcUserService;
    private Logger logger = LoggerFactory.getLogger(UserController.class);


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBody userServiceLogin(@RequestBody Map<String, Object> params) {
        logger.info("userServiceLogin " + params.toString());
        return iPcUserService.login(params);
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ResponseBody userServiceRegister(@RequestBody Map<String, Object> params) {
        logger.info("userServiceRegister " + params.toString());
        return iPcUserService.register(params);
    }
}
