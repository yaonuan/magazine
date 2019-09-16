package com.magazine.seckill.controller;

import com.magazine.seckill.service.SeckillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 秒杀controller
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/15
 */
@RestController
public class SeckillController {

    @Autowired
    private SeckillService seckillService;

    @RequestMapping("/redis/seckill")
    public String secKill(int uid, int skuId) {
        return seckillService.seckill(uid, skuId);
    }

}
