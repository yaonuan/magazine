package com.magazine.red_packet.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.magazine.red_packet.entity.RedPacketInfo;
import com.magazine.red_packet.entity.RedPacketRecord;
import com.magazine.red_packet.mapper.RedPacketInfoMapper;
import com.magazine.red_packet.mapper.RedPacketRecordMapper;
import com.magazine.red_packet.service.RedPacketInfoService;
import com.magazine.service.RedisService;
import com.magazine.utils.SnowFlake;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Random;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/5
 */
@Service
public class RedPacketInfoServiceImpl extends ServiceImpl<RedPacketInfoMapper, RedPacketInfo> implements RedPacketInfoService {

    @Autowired
    private RedPacketInfoMapper packetInfoMapper;

    @Autowired
    private RedisService redisService;

    @Autowired
    private RedPacketRecordMapper redPacketRecordMapper;

    private static final String TOTAL_NUM = "_totalNum";

    private static final String TOTAL_AMAOUNT = "_totalAmount";

    @Override
    public String saveRedPacket(Integer uid, Integer totalNum, Integer totalAmount) {
        // 新增红包
        RedPacketInfo redPacketInfo = new RedPacketInfo();
        redPacketInfo.setUid(uid);
        redPacketInfo.setTotalAmount(totalAmount);
        redPacketInfo.setTotalPacket(totalNum);
        redPacketInfo.setCreateTime(new Date());
        // 使用雪花算法构建唯一性的红包ID
        Long redPacketId = new SnowFlake(1, 1).nextId();
        redPacketInfo.setRedPacketId(redPacketId);
        redPacketInfo.setRemainingAmount(totalAmount);
        redPacketInfo.setRemainingPacket(totalNum);
        this.insert(redPacketInfo);
        // redis添加缓存
        redisService.set(redPacketId + TOTAL_NUM, totalNum + "");
        redisService.set(redPacketId + TOTAL_AMAOUNT, totalAmount + "");
        return "success";
    }


    @Override
    public String getRedPacket(Integer uid, long redPacketId) {
        Integer randomAmount = 0;
        // 红包总数量
        String num = String.valueOf(redisService.get(redPacketId + TOTAL_NUM));
        // 红包总金额
        String totalAmount = String.valueOf(redisService.get(redPacketId + TOTAL_AMAOUNT));
        if (!StringUtils.isNotBlank(num) || Integer.parseInt(num) == 0) {
            return "抱歉！红包已经抢完了";
        }
        if (StringUtils.isNotBlank(totalAmount)) {
            Integer totalAmountInt = Integer.parseInt(totalAmount);
            Integer maxMoney = totalAmountInt / Integer.parseInt(num) * 2;
            Random random = new Random();
            randomAmount = random.nextInt(maxMoney);
        }
        // TODO lua脚本操作
        redisService.decr(redPacketId + "_totalNum", 1);
        redisService.decr(redPacketId + TOTAL_AMAOUNT, randomAmount);
        updateRacketInDB(uid, redPacketId, randomAmount);
        return randomAmount + "";
    }


    private void updateRacketInDB(Integer uid, long redPacketId, Integer amount) {
        // 新增红包记录
        RedPacketRecord record = new RedPacketRecord();
        record.setUid(uid);
        record.setRedPacketId(redPacketId);
        // 红包抢的金额计算
        record.setAmount(amount);
        record.setCreateTime(new Date());
        redPacketRecordMapper.insert(record);
        // 查出红包info的信息
        RedPacketInfo oldPacket = packetInfoMapper.selectByRedPacketId(redPacketId);
        // 更新红包信息
        RedPacketInfo redPacketInfo = new RedPacketInfo();
        redPacketInfo.setId(oldPacket.getId());
        redPacketInfo.setRemainingPacket(oldPacket.getRemainingPacket() - 1);
        redPacketInfo.setRemainingAmount(oldPacket.getRemainingAmount() - amount);
        redPacketInfo.setUpdateTime(new Date());
        packetInfoMapper.updateById(redPacketInfo);
    }

}
