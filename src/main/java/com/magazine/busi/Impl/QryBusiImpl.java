package com.magazine.busi.Impl;

import com.magazine.busi.QryBusi;
import com.magazine.domain.ScrapyGovPolicyEntity;
import com.magazine.utils.SqlSessionUtil;
import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/27
 */
public class QryBusiImpl implements QryBusi {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public List<Long> selectPolicyId() {
        SqlSession our = SqlSessionUtil.getSqlSession("our");
        List<Long> ids = null;
        try {
            ids = our.selectList("com.magazine.mapper.RealPolicyMapper.selectPolicyId");
        }catch (Exception e){
            logger.error("查询发生异常～");
        }finally {
            our.close();
        }

        return ids;
    }

    @Override
    public List<ScrapyGovPolicyEntity> queryByIds(List<Long> ids) {
        SqlSession our = SqlSessionUtil.getSqlSession("our");
        List<ScrapyGovPolicyEntity> list = new ArrayList<>();
        String s = ids.toString();
        String substring = s.substring(1, s.length() - 1);
        try {
            list = our.selectList("com.magazine.mapper.RealPolicyMapper.queryByIds",substring);
        } catch (Exception e) {
            e.printStackTrace();
//            logger.error("查询政策信息失败～");
        }finally {
            our.close();
        }
        return list;
    }
}
