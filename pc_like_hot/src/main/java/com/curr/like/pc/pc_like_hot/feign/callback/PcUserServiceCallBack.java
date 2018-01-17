package com.curr.like.pc.pc_like_hot.feign.callback;

import com.curr.like.pc.pc_like_hot.feign.IPcUserService;
import com.curry.like.model.model.entity.ResponseBody;
import org.springframework.stereotype.Component;

import java.util.Map;
@Component
public class PcUserServiceCallBack implements IPcUserService {

    @Override
    public ResponseBody register(Map<String, Object> params) {
        return ResponseBody.builder().code(10001).message("用户操作服务停止").build();
    }

    @Override
    public ResponseBody login(Map<String, Object> params) {
        return ResponseBody.builder().code(10001).message("用户操作服务停止").build();
    }
}
