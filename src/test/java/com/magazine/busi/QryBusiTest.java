package com.magazine.busi;

import com.magazine.busi.Impl.QryBusiImpl;
import com.magazine.domain.ScrapyGovPolicyEntity;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/27
 */
public class QryBusiTest {

    @Test
    public void selectPolicyId() {
        QryBusi qryBusi = new QryBusiImpl();
        List<Long> longs = qryBusi.selectPolicyId();
//        System.out.println(longs);

        List<Long> longs1 = longs.subList(0, 5);
        List<ScrapyGovPolicyEntity> list = qryBusi.queryByIds(longs1);
        System.out.println(list);


    }
}