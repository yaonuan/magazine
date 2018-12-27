package com.magazine.utils;

import com.magazine.MagazineApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagazineApplication.class)
public class CommonUtilsTest {

    @Test
    public void MD5() {
        String test = "admin";
        String MD5 =  CommonUtils.MD5(test);
        System.out.println(MD5);
    }
}