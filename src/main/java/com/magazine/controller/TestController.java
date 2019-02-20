package com.magazine.controller;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 测试类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-01-05
 */
@RestController
public class TestController {

    @Resource
    private RedisTemplate redisTemplate;

    @GetMapping("/redis/setAndGet")
    public String setAndGetValue(String name, String value) {
        redisTemplate.opsForValue().set(name, value);
        return (String) redisTemplate.opsForValue().get(name);
    }

    @GetMapping("/redis/get")
    public String getValue(String name){
        return (String) redisTemplate.opsForValue().get(name);
    }

}
