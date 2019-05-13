package com.magazine.service.impl;

import com.magazine.service.RangingService;
import com.magazine.service.RedisService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 排行榜
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-02-25
 */
@Service("RangingService")
public class RangingServiceImpl implements RangingService {

    private static final String RANKGNAME = "user_score";

    private static final String SALESCORE = "sale_score_rank:";

    @Resource
    private RedisService redisService;

    @Override
    public void rankAdd(String uid, Integer score) {
        redisService.zAdd(RANKGNAME, uid, score);
    }

    @Override
    public void increSocre(String uid, Integer score) {
        redisService.incrementScore(RANKGNAME, uid, score);
    }

    @Override
    public Long rankNum(String uid) {
        return redisService.zRank(RANKGNAME, uid);
    }

    @Override
    public Long score(String uid) {
        Long score = redisService.zSetScore(RANKGNAME, uid).longValue();
        return score;
    }


}
