package com.magazine.mapper;

import com.magazine.domain.CommentEntity;
import org.apache.ibatis.annotations.Mapper;

/**
 * 评价数据访问层
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-10-16
 */
@Mapper
public interface CommentMapper {

    /**
     * 自定义查询评价信息
     * @param commentEntity
     * @return
     */
    CommentEntity queryOne(CommentEntity commentEntity);

    /**
     * 新增评价数据信息
     * @param commentEntity
     * @return
     */
    int insertOne(CommentEntity commentEntity);

    /**
     * 更新评价数据信息
     * @param commentEntity
     * @return
     */
    int updateOne(CommentEntity commentEntity);
}
