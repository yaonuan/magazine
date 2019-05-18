package com.magazine.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.magazine.domain.ScrapyGovPolicyEntity;
import com.magazine.mapper.ScrapyGovPolicyMapper;
import com.magazine.service.ScrapyGovPolicyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/16
 */
@Service
public class ScrapyGovPolicyServiceImpl extends ServiceImpl<ScrapyGovPolicyMapper,ScrapyGovPolicyEntity> implements ScrapyGovPolicyService {

    @Autowired
    private  ScrapyGovPolicyMapper scrapyGovPolicyMapper;
    @Override
    public List<ScrapyGovPolicyEntity> queryList() {
        return scrapyGovPolicyMapper.queryList();
    }
}
