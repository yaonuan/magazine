package com.magazine.service;

import com.magazine.domain.UserEntity;

/**
 * user业务接口类
 */
public interface UserService {

    UserEntity queryOne(UserEntity userEntity);

    int insertOne(UserEntity userEntity);

    int updateOne(UserEntity userEntity);


}
