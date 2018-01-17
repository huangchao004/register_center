package com.curry.like.user.user_service.impl;

import com.curry.like.api.api_service.user.IUserServiceApi;
import com.curry.like.model.model.response.ResponseBody;
import com.curry.like.user.user_service.service.UserOperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserServiceImpl implements IUserServiceApi {

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Autowired
    private UserOperatorService userOperatorService;

    @Override
    public ResponseBody register(Map<String, Object> params) {
        logger.info("register " + params.toString());
        return userOperatorService.registerService(params);
    }

    @Override
    public ResponseBody login(Map<String, Object> params) {
        logger.info("login " + params.toString());
        return ResponseBody.builder().code(200).message("SUCCESS").build();
    }
}
