package com.magazine.busi;

import com.magazine.domain.ScrapyGovPolicyEntity;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 查询正式库数据
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/27
 */
public interface QryBusi {

    /**
     * 查询政策id
     *
     * @return
     */
    List<Long> selectPolicyId();

    /**
     * 查询政策
     *
     * @param ids
     * @return
     */
    List<ScrapyGovPolicyEntity> queryByIds(List<Long> ids);

}
