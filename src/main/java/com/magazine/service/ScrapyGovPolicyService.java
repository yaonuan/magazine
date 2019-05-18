package com.magazine.service;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.IService;
import com.magazine.domain.ScrapyGovPolicyEntity;

import java.util.List;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/16
 */
public interface ScrapyGovPolicyService extends IService<ScrapyGovPolicyEntity> {

    List<ScrapyGovPolicyEntity> queryList();

}
