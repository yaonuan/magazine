package com.magazine.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.magazine.domain.UserTokenEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserTokenMapper extends BaseMapper<UserTokenEntity> {

    UserTokenEntity queryByToken(String token);
}
