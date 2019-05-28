package com.magazine.service;

import com.magazine.domain.ScrapyGovPolicyEntity;

import java.util.List;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/16
 */
public interface MultiThreadDeconstructSerivce {

    /**
     * 多线程解构
     *
     * @param list
     * @return
     */
    boolean multiDeconstruct(List<ScrapyGovPolicyEntity> list);
}
