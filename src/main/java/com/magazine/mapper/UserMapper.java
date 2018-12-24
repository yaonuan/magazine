package com.magazine.mapper;

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
public interface UserMapper {


    /**
     * 自定义查询一个
     * @param userEntity
     * @return
     */
    UserEntity queryOne(UserEntity userEntity);

    /**
     * 根据openid查询
     * @param openid
     * @return
     */
    UserEntity queryByOpenId(@Param("openid") String openid);

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

}
