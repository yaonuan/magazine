package com.magazine.mapper;

import com.magazine.domain.ScrapyGovPolicyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 政策库查询mapper
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/27
 */
public interface RealPolicyMapper {

    /**
     * 查询所有的政策id
     *
     * @return
     */
    List<Long> selectPolicyId();

    /**
     * 根据id字符串查询政策相关信息
     *
     * @param value
     * @return
     */
    List<ScrapyGovPolicyEntity> queryByIds( String value);

}
