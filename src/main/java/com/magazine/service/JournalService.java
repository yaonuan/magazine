package com.magazine.service;

import com.alibaba.druid.sql.PagerUtils;
import com.baomidou.mybatisplus.service.IService;
import com.magazine.domain.JournalEntity;
import com.magazine.utils.PageUtils;

import java.util.Map;

public interface JournalService extends IService<JournalEntity> {

    /**
     * 分页查询
     * @param params
     * @return
     */
    PageUtils queryTerm(Map<String,Object> params);

    /**
     * 测试
     * @param title
     * @return
     */
    JournalEntity queryByTitle(String title);
}
