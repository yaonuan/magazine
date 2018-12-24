package com.magazine.service.impl;

import com.magazine.domain.UserEntity;
import com.magazine.mapper.UserMapper;
import com.magazine.service.UserService;
import com.magazine.utils.HttpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Map;

/**
 * user业务实现
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-10-19
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;


    @Override
    public UserEntity queryOne(UserEntity userEntity){
        return  userMapper.queryOne(userEntity);
    }

    @Override
    public int insertOne(UserEntity userEntity){
        return userMapper.insertOne(userEntity);
    }

    @Override
    public int updateOne(UserEntity userEntity){
        return  userMapper.updateOne(userEntity);
    }

}
