package com.magazine.service;

import com.baomidou.mybatisplus.service.IService;
import com.magazine.domain.UserTokenEntity;
import com.magazine.utils.R;

public interface UserTokenService extends IService<UserTokenEntity> {

    /**
     * 生成token
     *
     * @param userId
     *            用户ID
     */
    R createToken(long userId);

    /**
     * 退出，修改token值
     *
     * @param userId
     *            用户ID
     */
    void logout(long userId);
}
