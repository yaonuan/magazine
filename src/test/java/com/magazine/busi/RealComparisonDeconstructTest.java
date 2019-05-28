package com.magazine.busi;

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
 * @Date : 2019/5/28
 */
@SpringBootTest(classes = MagazineApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class RealComparisonDeconstructTest {
    @Autowired
    private RealComparisonDeconstruct deconstruct;

    @Test
    public void test(){
        deconstruct.comparison();
    }

}