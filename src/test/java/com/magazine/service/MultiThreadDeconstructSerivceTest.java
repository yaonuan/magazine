package com.magazine.service;

import com.magazine.MagazineApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

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

    @Test
    public void test(){
        serivce.test();
    }

}