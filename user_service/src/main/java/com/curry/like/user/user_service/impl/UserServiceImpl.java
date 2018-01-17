package com.curry.like.user.user_service.impl;

import com.curry.like.api.api_service.user.IUserServiceApi;
import com.curry.like.model.model.entity.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
public class UserServiceImpl implements IUserServiceApi {

    private Logger  logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public ResponseBody register(Map<String, Object> params) {
        logger.info("register "+params.toString());

        return ResponseBody.builder().code(200).message("SUCCESS").build();
    }

    @Override
    public ResponseBody login(Map<String, Object> params) {
        logger.info("login "+params.toString());
        return ResponseBody.builder().code(200).message("SUCCESS").build();
    }
}
