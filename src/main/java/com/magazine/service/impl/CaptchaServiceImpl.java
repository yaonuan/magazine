package com.magazine.service.impl;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.google.code.kaptcha.Producer;
import com.magazine.domain.CaptchaEntity;
import com.magazine.exception.RRException;
import com.magazine.mapper.CaptchaMapper;
import com.magazine.service.CaptchaService;
import com.magazine.utils.DateUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 验证码实现类
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-27
 */
@Service
public class CaptchaServiceImpl extends ServiceImpl<CaptchaMapper, CaptchaEntity> implements CaptchaService {

    @Autowired
    private Producer producer;

    @Override
    public BufferedImage getCaptcha(String uuid) {
        if (StringUtils.isBlank(uuid)) {
            throw new RRException("uuid不能为空");
        }
        // 生成文字验证码
        String code = producer.createText();

        CaptchaEntity captchaEntity = new CaptchaEntity();
        captchaEntity.setUuid(uuid);
        captchaEntity.setCode(code);
        // 5分钟后过期
        captchaEntity.setExpireTime(DateUtils.addDateMinutes(new Date(), 5));
        this.insert(captchaEntity);

        return producer.createImage(code);
    }

    @Override
    public boolean validate(String uuid, String code) {
        CaptchaEntity captchaEntity = this.selectOne(new EntityWrapper<CaptchaEntity>().eq("uuid", uuid));
        if (captchaEntity == null) {
            return false;
        }

        // 删除验证码
        // todo 正式环境去注释 this.deleteById(uuid);
//        this.deleteById(uuid);

        return captchaEntity.getCode().equalsIgnoreCase(code) && captchaEntity.getExpireTime().getTime() >= System
                .currentTimeMillis();

    }
}
