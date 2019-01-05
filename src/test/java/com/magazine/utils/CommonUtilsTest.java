package com.magazine.utils;

import com.magazine.MagazineApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagazineApplication.class)
public class CommonUtilsTest {

    @Test
    public void MD5() {
        String test = "admin";
        String MD5 = CommonUtils.MD5(test);
        System.out.println(MD5);
    }

    @Test
    public void Url() {
        String bucketName = "oss-cn-shanghai.aliyuncs.com";
        String endpoint = "xiaomo-oss";
        String fileUrl = "image/2019-01-06/272df2d58d7e45f5b4f2d7318716b5bb-rexue_kuanglan-002.jpg";
        String a =  "https://"+ bucketName + "."+endpoint +"/"+fileUrl;
        System.out.println(a);
    }



}