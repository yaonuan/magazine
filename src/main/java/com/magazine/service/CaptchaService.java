package com.magazine.service;

import com.baomidou.mybatisplus.service.IService;
import com.magazine.domain.CaptchaEntity;

import java.awt.image.BufferedImage;

/**
 * 验证码相关
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-27
 */
public interface CaptchaService extends IService<CaptchaEntity> {

    /**
     * 获取图片验证码
     */
    BufferedImage getCaptcha(String uuid);

    /**
     * 验证码效验
     *
     * @param uuid
     *            uuid
     * @param code
     *            验证码
     * @return true：成功 false：失败
     */
    boolean validate(String uuid, String code);

}
