package com.curr.like.pc.pc_like_hot;

import com.curr.like.pc.pc_like_hot.feign.IPcUserService;
import com.curry.like.model.model.entity.ResponseBody;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class TestController {

    @Autowired
    private IPcUserService iPcUserService;
    private Logger logger = LoggerFactory.getLogger(TestController.class);


    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ResponseBody userServiceLogin(@RequestBody Map<String, Object> params) {
        logger.info("userServiceLogin "+params.toString());
        return iPcUserService.login(params);
    }

}
