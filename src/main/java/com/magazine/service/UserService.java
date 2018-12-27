package com.magazine.service;

import com.baomidou.mybatisplus.service.IService;
import com.magazine.domain.UserEntity;

/**
 * 用户相关
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-27
 */
public interface UserService extends IService<UserEntity> {

    /**
     * 根据用户名查询
     * @param name
     * @return
     */
    UserEntity queryByUserName(String name);


}
