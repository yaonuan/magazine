package com.magazine.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.magazine.domain.UserTokenEntity;
import com.magazine.mapper.UserTokenMapper;
import com.magazine.oauth2.TokenGenerator;
import com.magazine.service.UserTokenService;
import com.magazine.utils.R;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 用户token实现类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-27
 */
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserTokenEntity> implements UserTokenService {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public R createToken(long userId) {

        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        UserTokenEntity tokenEntity = this.selectById(userId);
        if(tokenEntity == null){
            tokenEntity = new UserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.insert(tokenEntity);
        }else{
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }

        R r = R.ok().put("token", token).put("expire", EXPIRE);

        return r;
    }

    @Override
    public void logout(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //修改token
        UserTokenEntity tokenEntity = new UserTokenEntity();
        tokenEntity.setUserId(userId);
        tokenEntity.setToken(token);
        this.updateById(tokenEntity);
    }
}
