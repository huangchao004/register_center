package com.curr.like.pc.pc_like_hot.feign;

import com.curry.like.api.api_service.user.IUserServiceApi;
import org.springframework.cloud.netflix.feign.FeignClient;

@FeignClient(value = "USER-SERVICE")
public interface IPcUserService extends IUserServiceApi {


}
