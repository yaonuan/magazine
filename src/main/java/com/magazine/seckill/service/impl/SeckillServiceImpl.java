package com.magazine.seckill.service.impl;

import com.magazine.bloom_filter.entity.SysUser;
import com.magazine.seckill.service.SeckillService;
import com.magazine.service.RedisService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/15
 */
@Service
public class SeckillServiceImpl implements SeckillService {

    private static final String secStartPrefix = "skuId_start_";
    private static final String secAccess = "skuId_access_";
    private static final String secCount = "skuId_count_";
    private static final String filterName = "skuId_bloomfilter_";
    private static final String bookedName = "skuId_booked_";

    @Autowired
    private RedisService redisService;

    @Override
    public String seckill(int uid, int skuId) {
        // 流量拦截层
        // 1、判断秒杀是否开始 0_1568557080 开始标示_开始时间（时间戳）
        String isStart = (String) redisService.get(secStartPrefix + skuId);
        if (StringUtils.isBlank(isStart)) {
            return "秒杀还未开始";
        }
        if (isStart.contains("_")) {
            int isStartInt = Integer.parseInt(isStart.split("_")[0]);
            int startTime = Integer.parseInt(isStart.split("_")[1]);
            if (isStartInt == 0) {
                if (startTime > getNow()) {
                    return "秒杀还未开始";
                } else {
                    redisService.set(secStartPrefix + skuId, 1 + "");
                }
            } else {
                return "系统异常";
            }
        } else {
            if (Integer.parseInt(isStart) != 1) {
                return "系统异常";
            }
        }
        // 2、流量拦截
        String skuIdAccessName = secAccess + skuId;
        Integer accessNumInt = 0;
        String accessNum = (String) redisService.get(skuIdAccessName);
        if (StringUtils.isNotBlank(accessNum)) {
            accessNumInt = Integer.parseInt(accessNum);
        }
        String skuIdCountName = secCount + skuId;
        Integer countNumInt = Integer.parseInt((String) redisService.get(skuIdCountName));
        if (countNumInt * 1.2 < accessNumInt) {
            return "抢购已经完成，欢迎下次参与";
        } else {
            redisService.incr(skuIdAccessName, 1);
        }
        // 3、信息校验层
        if (redisService.bloomFilterExists(filterName + skuId, uid)) {
            return "您已经抢购过该商品请勿重复下单！";
        } else {
            redisService.bloomFilterAdd(filterName + skuId, uid);
        }
        Boolean isSuccess = redisService.getAndIncrLua(bookedName + skuId);
        if (isSuccess) {
            // TODO 订单完善异步完成MQ操作
            return "恭喜你抢购成功";
        } else {
            return "抢购已经完成，欢迎下次参与";
        }
    }


    private long getNow() {
        return System.currentTimeMillis() / 1000;
    }
}
