package com.magazine.bloom_filter.controller;

import com.magazine.bloom_filter.service.BloomfilterService;
import com.magazine.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 布隆过滤器
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/11
 */
@RestController
public class BloomFilterController {

    @Autowired
    private BloomfilterService bloomfilterService;

    @Autowired
    private RedisService redisService;

    @RequestMapping("/bloom/idExists")
    public boolean ifExists(Integer id) {
        return bloomfilterService.userIdExists(id);
    }

    @RequestMapping("/bloom/redisIdAdd")
    public boolean redisIdAdd(Integer id) {
        return redisService.bloomFilterAdd(id);
    }

    @RequestMapping("/bloom/redisIdExists")
    public boolean redisIdExists(Integer id) {
        return redisService.bloomFilterExists(id);
    }

}
