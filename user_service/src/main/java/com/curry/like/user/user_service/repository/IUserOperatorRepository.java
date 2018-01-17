package com.curry.like.user.user_service.repository;

import com.curry.like.user.user_service.entity.TAccountInfoEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户操作的repository
 */
public interface IUserOperatorRepository extends CrudRepository<TAccountInfoEntity, Integer> {

    TAccountInfoEntity queryByUsernameAndPassword(String username, String password);
    TAccountInfoEntity queryByUsername(String username);

    TAccountInfoEntity queryByEmailAndPassword(String email, String password);

}
