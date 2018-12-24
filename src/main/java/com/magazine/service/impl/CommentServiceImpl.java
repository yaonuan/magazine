package com.magazine.service.impl;

import com.magazine.domain.CommentEntity;
import com.magazine.mapper.CommentMapper;
import com.magazine.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 评价实现业务层
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-10-19
 */
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    CommentMapper commentMapper;

    @Override
    public CommentEntity queryOne(CommentEntity commentEntity){
        return commentMapper.queryOne(commentEntity);
    }

    @Override
    public int insertOne(CommentEntity commentEntity){
        return commentMapper.insertOne(commentEntity);
    }

    @Override
    public int updateOne(CommentEntity commentEntity){
        return commentMapper.updateOne(commentEntity);
    }

}
