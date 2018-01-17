package com.curry.like.user.user_service.service;

import com.curry.like.api.api_service.util.DateUtils;
import com.curry.like.api.api_service.util.MD5Util;
import com.curry.like.api.api_service.util.RandomUtils;
import com.curry.like.model.model.response.ResponseBody;
import com.curry.like.model.model.response.ResponseCode;
import com.curry.like.user.user_service.entity.TAccountInfoEntity;
import com.curry.like.user.user_service.repository.IUserOperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.Map;

@Service
public class UserOperatorService {

    @Autowired
    private IUserOperatorRepository iUserOperatorRepository;
    @PersistenceContext
    private EntityManager entityManager;

    public ResponseBody registerService(Map<String, Object> params) {
        TAccountInfoEntity tAccountInfoEntity = new TAccountInfoEntity();
        if (params.containsKey("username ") && params.containsKey("password")
                && params.containsKey("nickName")) {
            String username = (String) params.get("username");
            String password = (String) params.get("password");
            String nickName = (String) params.get("nickName");
            String email = (String) params.get("email");
            TAccountInfoEntity accountInfoEntity = iUserOperatorRepository.queryByUsername(username);
            if (accountInfoEntity == null) {
                tAccountInfoEntity.setAccountId(RandomUtils.getSerialNo());
                tAccountInfoEntity.setCreateTime(DateUtils.getCurrentDate("yyyy-MM-dd HH:mm:ss"));
                tAccountInfoEntity.setNickName(nickName);
                tAccountInfoEntity.setUsername(username);
                tAccountInfoEntity.setPassword(password);
                tAccountInfoEntity.setSalt(MD5Util.MD5Encode(RandomUtils.getMiddlePaySerialNo(), "UTF-8"));
                tAccountInfoEntity.setEmail(email);
                iUserOperatorRepository.save(tAccountInfoEntity);
                entityManager.flush();
                return ResponseBody.builder().code(ResponseCode.SUCCESS.getCode()).message(ResponseCode.SUCCESS.getMessage()).obj(tAccountInfoEntity.getAccountId()).build();
            } else {
                return ResponseBody.builder().code(ResponseCode.USERNAME_EXISTS.getCode()).message(ResponseCode.USERNAME_EXISTS.getMessage()).obj(tAccountInfoEntity.getAccountId()).build();
            }
        } else {
            return ResponseBody.builder().code(ResponseCode.NECESSARY_PARAMS_EMPTY.getCode()).message(ResponseCode.NECESSARY_PARAMS_EMPTY.getMessage()).build();
        }
    }
}
