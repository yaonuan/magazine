package com.magazine.controller;

import com.magazine.MagazineApplication;
import com.magazine.domain.UserEntity;
import com.magazine.form.LoginForm;
import com.magazine.service.CaptchaService;
import com.magazine.service.UserService;
import com.magazine.service.UserTokenService;
import com.magazine.utils.CommonUtils;
import com.magazine.utils.R;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = MagazineApplication.class)
public class LoginControllerTest {

    @Autowired
    CaptchaService captchaService;

    @Autowired
    UserService userService;

    @Autowired
    UserTokenService userTokenService;

    @Test
    public void login() {
        LoginForm form = new LoginForm();
        form.setUuid("941844e5-e431-4f18-8912-0524638222d4");
        form.setUsername("admin");
        form.setPassword("admin");
        form.setCaptcha("x8mw3");


        boolean captcha = captchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            System.out.println("验证码不正确");
        }

        // 用户信息
        UserEntity user = userService.queryByUserName(form.getUsername());

        // 账号不存在、密码错误
        if (user == null || !user.getPassword().equals(CommonUtils.MD5(form.getPassword()))) {
            System.out.println("账号或密码不正确");
        }

        // 生成token，并保存到数据库
        R r = userTokenService.createToken(user);

    }
}