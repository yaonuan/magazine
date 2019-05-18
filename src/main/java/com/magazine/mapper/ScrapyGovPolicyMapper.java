package com.magazine.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.magazine.domain.ScrapyGovPolicyEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/16
 */
@Mapper
public interface ScrapyGovPolicyMapper extends BaseMapper<ScrapyGovPolicyEntity> {

    List<ScrapyGovPolicyEntity> queryList();
}
