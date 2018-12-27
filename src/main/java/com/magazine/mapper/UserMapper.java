package com.magazine.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.magazine.domain.UserEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 用户数据访问层
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-10-16
 */
@Mapper
public interface UserMapper extends BaseMapper<UserEntity> {


    /**
     * 新增
     * @param userEntity
     * @return
     */
    int insertOne(UserEntity userEntity);

    /**
     * 更新一条信息
     * @param userEntity
     * @return
     */
    int updateOne(UserEntity userEntity);

    /**
     * 根据用户名，查询系统用户
     * @param name
     * @return
     */
    UserEntity queryByUserName(@Param("name") String name);

}
