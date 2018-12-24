package com.magazine.service;

import com.magazine.domain.CommentEntity;

/**
 * 评价实现业务类
 */
public interface CommentService {

    /**
     * 自定义查看集的数据
     * @param commentEntity
     * @return
     */
    CommentEntity queryOne(CommentEntity commentEntity);

    /**
     * 新增集的数据
     * @param commentEntity
     * @return
     */
    int insertOne(CommentEntity commentEntity);

    /**
     * 更新集数据
     * @param commentEntity
     * @return
     */
    int updateOne(CommentEntity commentEntity);
}
