package com.magazine.service;

import com.magazine.MagazineApplication;
import com.magazine.domain.ScrapyGovPolicyEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/5/16
 */
@SpringBootTest(classes = MagazineApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class MultiThreadDeconstructSerivceTest {

    @Autowired
    private MultiThreadDeconstructSerivce serivce;

    @Autowired
    private ScrapyGovPolicyService scrapyGovPolicyService;

    @Test
    public void test(){
        List<ScrapyGovPolicyEntity> list = scrapyGovPolicyService.queryList();
        serivce.multiDeconstruct(list);
//        serivce.test();
    }

}