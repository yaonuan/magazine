package com.magazine.controller;

import com.magazine.domain.UserEntity;
import com.magazine.mapper.UserMapper;
import com.magazine.service.RedisService;
import com.magazine.service.UserService;
import com.magazine.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 用户控制层
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-10-19
 */
@RestController
@RequestMapping(value = "api/v1/user")
public class UserController {

    private static final String key = "userCache_";
    @Resource
    private UserService userService;
    @Autowired
    private RedisService redisService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping("/name")

    public R queryByName(String name) {
        UserEntity userEntity = userService.queryByUserName(name);
        return R.ok().put("data", userEntity);
    }

    @GetMapping("/getUser")
    public UserEntity getUser(Integer id) {
        return userMapper.selectById(id);
    }

    @GetMapping("/getUserCache")
    public UserEntity getUserCache(Integer id) {
        UserEntity user = (UserEntity) redisService.get(key + id);
        if (user == null) {
            UserEntity userDB = userMapper.selectById(id);
            System.out.println("fresh value from DB id:" + id);
            if (userDB != null) {
                redisService.set(key + id, userDB);
                return userDB;
            }
        }
        return user;
    }


}
