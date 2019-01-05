package com.magazine.service;

import com.baomidou.mybatisplus.service.IService;
import com.magazine.domain.UserEntity;
import com.magazine.domain.UserTokenEntity;
import com.magazine.utils.R;

public interface UserTokenService extends IService<UserTokenEntity> {

    /**
     * 生成token
     *
     */
    R createToken(UserEntity userEntity);

    /**
     * 退出，修改token值
     *
     */
    void logout(UserEntity userEntity);
}
