package com.magazine.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.magazine.domain.UserEntity;
import com.magazine.mapper.UserMapper;
import com.magazine.service.UserService;
import com.magazine.vo.BaseVo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * user业务实现
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-10-19
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {

    @Autowired
    UserMapper userMapper;

    @Override
    public UserEntity queryByUserName(String name) {
        return userMapper.queryByUserName(name);
    }

    @Override
    public List<BaseVo> queryUserName() {
        List<UserEntity> list = this.selectList(new EntityWrapper<>());
        if (list == null || list.size() == 0) {
            return null;
        }
        List<BaseVo> result = new ArrayList<>();
        for (UserEntity entity : list) {
            BaseVo baseVo = new BaseVo();
            BeanUtils.copyProperties(entity, baseVo);
            result.add(baseVo);
        }
        return result;
    }

}
