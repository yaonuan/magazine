package com.magazine.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.magazine.domain.CaptchaEntity;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CaptchaMapper extends BaseMapper<CaptchaEntity> {
}
