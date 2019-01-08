package com.magazine.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.magazine.domain.JournalEntity;
import com.magazine.mapper.JournalMapper;
import com.magazine.service.JournalService;
import com.magazine.utils.PageUtils;
import com.magazine.utils.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;

/**
 * 杂志实现类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-25
 */
@Service
public class JournalServiceImpl extends ServiceImpl<JournalMapper, JournalEntity> implements JournalService {

    @Autowired
    JournalMapper journalMapper;

    @Override
    public PageUtils<JournalEntity> queryTerm(Map<String, Object> params) {
        Page<JournalEntity> page = this.selectPage(new Query<JournalEntity>(params).getPage(),
                new EntityWrapper<>());
        return new PageUtils(page);
    }

    @Override
    public Boolean save(JournalEntity entity) {
        entity.setCreateTime(new Date());
        return this.insert(entity);
    }

    @Override
    public Boolean replace(JournalEntity entity) {
        return this.updateById(entity);
    }

    @Override
    public Boolean setOnline(Long id) {


        return null;
    }
}
