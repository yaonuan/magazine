package com.magazine.controller;

import com.magazine.domain.UserEntity;
import com.magazine.service.UserService;
import com.magazine.utils.R;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    @Autowired
    private UserService userService;

    @GetMapping("/name")
    public R queryByName(String name){
        UserEntity userEntity = userService.queryByUserName(name);
        return R.ok().put("data", userEntity);
    }

}
