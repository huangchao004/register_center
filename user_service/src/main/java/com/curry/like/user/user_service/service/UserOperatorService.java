package com.curry.like.user.user_service.service;

import com.curry.like.model.model.entity.ResponseBody;
import com.curry.like.model.model.entity.TAccountInfoEntity;
import com.curry.like.user.user_service.repository.IUserOperatorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.jpa.EntityManagerFactoryUtils;
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
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String nickName = (String) params.get("nickName");
        String email = (String) params.get("email");
//        tAccountInfoEntity.setAccountId();
//        tAccountInfoEntity.setCreateTime();
        iUserOperatorRepository.save(tAccountInfoEntity);
        entityManager.flush();
        return null;
    }
}
