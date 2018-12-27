package com.magazine.service.impl;

import com.magazine.MagazineApplication;
import com.magazine.domain.UserEntity;
import com.magazine.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagazineApplication.class)
public class UserServiceImplTest {

    @Autowired
    UserService userService;

    @Test
    public void queryByUserName() {
        String name = "xiaomo";
        UserEntity userEntity = userService.queryByUserName(name);
        System.out.println(userEntity);
    }
}