package com.magazine.busi.Impl;

import com.magazine.busi.QryBusi;
import com.magazine.busi.RealComparisonDeconstruct;
import com.magazine.deconstruct.PolicyDeconstructionMapper;
import com.magazine.domain.ScrapyGovPolicyEntity;
import com.magazine.service.MultiThreadDeconstructSerivce;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/28
 */
@Service
public class RealComparisonDeconstructImpl implements RealComparisonDeconstruct {


    @Autowired
    private PolicyDeconstructionMapper deconstructionMapper;
    @Autowired
    private MultiThreadDeconstructSerivce multiThreadDeconstruct;

    @Override
    public boolean comparison() {
        // 获取所有数据
        QryBusi qryBusi = new QryBusiImpl();
        List<Long> ids = qryBusi.selectPolicyId();
        List<List<Long>> batchPart = batchPart(ids, 20);
        for (List<Long> list : batchPart) {
            // 获取已解构的数据
            List<Long> allId = deconstructionMapper.queryAllId();
            // 查询list中与allId不同的部分
            List<Long> different = getDifferent(list, allId);
            if (different != null && different.size() > 0){
                List<ScrapyGovPolicyEntity> policyEntityList = qryBusi.queryByIds(different);
                if (policyEntityList != null && policyEntityList.size() > 0){
                    multiThreadDeconstruct.multiDeconstruct(policyEntityList);
                }
            }
        }
        return true;
    }


    /**
     * 切分数据
     *
     * @param list    需切分数据
     * @param oneSize 每一份数据的长度
     * @param <T>     数据类型
     * @return
     */
    private <T> List<List<T>> batchPart(List<T> list, int oneSize) {
        boolean flag = true;
        List<List<T>> result = new ArrayList<>();
        List<T> subList;
        int currentNum = 0;
        while (flag) {
            if (list.size() > oneSize * (currentNum + 1)) {
                subList = list.subList(currentNum * oneSize, oneSize * (currentNum + 1));
            } else {
                subList = list.subList(currentNum * oneSize, list.size());
                flag = false;
            }
            result.add(subList);
            currentNum++;
        }
        return result;
    }

    /**
     * 根据基础数据找出源数据中的其他数据
     *
     * @param one 源数据
     * @param two 基数据
     * @return
     */
    private List<Long> getDifferent(List<Long> one, List<Long> two) {
        List<Long> result = new ArrayList<>();
        Set<Long> twoSet = new HashSet<>();
        twoSet.addAll(two);
        for (Long single : one) {
            if (!twoSet.contains(single)) {
                result.add(single);
            }
        }
        return result;
    }

}
