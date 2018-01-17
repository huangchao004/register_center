package com.curry.like.api.api_service.user;

import com.curry.like.model.model.response.ResponseBody;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
public interface IUserServiceApi {


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    ResponseBody register(@RequestBody Map<String, Object> params);

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    ResponseBody login(@RequestBody Map<String, Object> params);




}
