package com.magazine.service;

import com.baomidou.mybatisplus.service.IService;
import com.magazine.domain.JournalEntity;
import com.magazine.utils.PageUtils;

import java.util.Map;

public interface JournalService extends IService<JournalEntity> {

    /**
     * 分页查询
     *
     * @param params
     * @return
     */
    PageUtils<JournalEntity> queryTerm(Map<String, Object> params);

    /**
     * 保存杂志信息
     *
     * @param entity
     * @return
     */
    Boolean save(JournalEntity entity);

    /**
     * 更新杂志信息
     *
     * @param entity
     * @return
     */
    Boolean replace(JournalEntity entity);

    /**
     * 审核或取消审核
     *
     * @param id
     * @return
     */
    Boolean setOnline(Long id);
}
