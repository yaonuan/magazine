package com.magazine.domain;

import lombok.Data;

/**
 * 登录表单
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-1-6
 */
@Data
public class LoginForm {
    /**
     * 用户名
     */
    private String username;
    /**
     * 密码
     */
    private String password;
    /**
     * 验证码
     */
    private String captcha;
    /**
     * 验证码标示
     */
    private String uuid;

}
