package com.magazine.seckill.service;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/15
 */
public interface SeckillService {

    /**
     * 秒杀
     *
     * @param uid   用户id
     * @param skuId 秒杀商品的id
     * @return
     */
    String seckill(int uid, int skuId);
}
