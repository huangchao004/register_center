package com.curry.like.user.user_service.service;

import com.curry.like.api.api_service.util.*;
import com.curry.like.model.model.response.ResponseBody;
import com.curry.like.model.model.response.ResponseCode;
import com.curry.like.user.user_service.entity.TAccountInfoEntity;
import com.curry.like.user.user_service.repository.IUserOperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.HashMap;
import java.util.Map;

@Service
public class UserOperatorService {

    @Autowired
    private IUserOperatorRepository iUserOperatorRepository;
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public ResponseBody registerService(Map<String, Object> params) {
        TAccountInfoEntity tAccountInfoEntity = new TAccountInfoEntity();
        int keySize = params.keySet().size();
        if (keySize == 0) {
            return ResponseBody.builder().code(ResponseCode.NECESSARY_PARAMS_EMPTY.getCode()).message(ResponseCode.NECESSARY_PARAMS_EMPTY.getMessage()).build();
        }
        if (BasicUtils.isCheckParamsKey(params, new Object[]{"username", "password", "nickName"})) {
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

    public ResponseBody loginService(Map<String, Object> params) {
        if (BasicUtils.isCheckParamsKey(params, new Object[]{"username", "password"})) {
            String username = (String) params.get("username");
            String password = (String) params.get("password");
            TAccountInfoEntity tAccountInfoEntity = iUserOperatorRepository.queryByUsernameAndPassword(username, password);
            if (tAccountInfoEntity == null) {
                return ResponseBody.builder().code(ResponseCode.USER_NOT_EXISTS.getCode())
                        .message(ResponseCode.USER_NOT_EXISTS.getMessage())
                        .build();
            }
            //用户存在
            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("username", username);
            tokenParams.put("accountId", tAccountInfoEntity.getAccountId());
            String token = JwtHelper.generateToken(tokenParams);
            return ResponseBody.builder().code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .obj(token)
                    .build();
        } else if (BasicUtils.isCheckParamsKey(params, new Object[]{"email", "password"})) {
            //邮箱登录
            String email = (String) params.get("email");
            String password = (String) params.get("password");
            TAccountInfoEntity tAccountInfoEntity = iUserOperatorRepository.queryByEmailAndPassword(email, password);
            if (tAccountInfoEntity == null) {
                return ResponseBody.builder().code(ResponseCode.USER_NOT_EXISTS.getCode())
                        .message(ResponseCode.USER_NOT_EXISTS.getMessage())
                        .build();
            }
            Map<String, Object> tokenParams = new HashMap<>();
            tokenParams.put("username", email);
            tokenParams.put("accountId", tAccountInfoEntity.getAccountId());
            String token = JwtHelper.generateToken(tokenParams);
            return ResponseBody.builder().code(ResponseCode.SUCCESS.getCode())
                    .message(ResponseCode.SUCCESS.getMessage())
                    .obj(token)
                    .build();
        }
        return ResponseBody.builder().code(ResponseCode.USER_NOT_EXISTS.getCode())
                .message(ResponseCode.USER_NOT_EXISTS.getMessage())
                .build();
    }


}
