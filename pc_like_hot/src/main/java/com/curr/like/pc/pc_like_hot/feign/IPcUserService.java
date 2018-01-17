package com.curr.like.pc.pc_like_hot.feign;

import com.curr.like.pc.pc_like_hot.feign.callback.PcUserServiceCallBack;
import com.curry.like.api.api_service.user.IUserServiceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "USER-SERVICE",fallback = PcUserServiceCallBack.class)
public interface IPcUserService extends IUserServiceApi {


}
