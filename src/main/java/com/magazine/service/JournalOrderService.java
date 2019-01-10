package com.magazine.service;

import com.baomidou.mybatisplus.service.IService;
import com.magazine.domain.JournalOrderEntity;
import com.magazine.utils.PageUtils;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface JournalOrderService extends IService<JournalOrderEntity> {

    /**
     * 订阅
     *
     * @param journalId
     * @param request
     * @return
     */
    Boolean saveOrder(Long journalId, HttpServletRequest request);

    /**
     * 根据用户id查询该用户订阅的信息
     *
     * @param userId
     * @return
     */
    PageUtils<JournalOrderEntity> queryByUserId(Map<String, Object> params,Long userId);

    /**
     * 根据条件查询订单信息
     *
     * @param params
     * @return
     */
    PageUtils<JournalOrderEntity> queryByOther(Map<String, Object> params);
}
