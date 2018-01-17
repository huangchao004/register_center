package com.curry.like.api.api_service.user;

import com.curry.like.model.model.entity.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

public interface IUserServiceApi {


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseBody register(@RequestBody Map<String, Object> params);

    @RequestMapping(value = "register", method = RequestMethod.POST)
    ResponseBody login(@RequestBody Map<String, Object> params);




}
