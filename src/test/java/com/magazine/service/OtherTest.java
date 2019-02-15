package com.magazine.service;

import com.magazine.MagazineApplication;
import com.magazine.domain.UserEntity;
import com.magazine.mapper.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-02-15
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagazineApplication.class)
public class OtherTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        String name = "admin";
        UserEntity userEntity = userMapper.queryByUserName(name);
        System.out.println(userEntity);
    }

}
