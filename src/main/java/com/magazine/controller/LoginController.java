package com.magazine.controller;

import com.magazine.domain.UserEntity;
import com.magazine.form.LoginForm;
import com.magazine.service.CaptchaService;
import com.magazine.service.UserService;
import com.magazine.service.UserTokenService;
import com.magazine.utils.CommonUtils;
import com.magazine.utils.R;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * 登录相关
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-27
 */
@RestController
public class LoginController {

    @Autowired
    CaptchaService captchaService;

    @Autowired
    UserService userService;

    @Autowired
    UserTokenService userTokenService;

    /**
     * 验证码
     */
    @GetMapping("captcha.jpg")
    public void captcha(HttpServletResponse response, String uuid) throws IOException {
        response.setHeader("Cache-Control", "no-store, no-cache");
        response.setContentType("image/jpeg");

        // 获取图片验证码
        BufferedImage image = captchaService.getCaptcha(uuid);

        ServletOutputStream out = response.getOutputStream();
        ImageIO.write(image, "jpg", out);
        IOUtils.closeQuietly(out);
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody LoginForm form) {
        boolean captcha = captchaService.validate(form.getUuid(), form.getCaptcha());
        if (!captcha) {
            return R.error("验证码不正确");
        }

        // 用户信息
        UserEntity user = userService.queryByUserName(form.getUsername());

        // 账号不存在、密码错误
        if (user == null || !user.getPassword().equals(CommonUtils.MD5(form.getPassword()))) {
            return R.error("账号或密码不正确");
        }

        // 生成token，并保存到数据库
        R r = userTokenService.createToken(user.getId());
        return r;
    }

}
