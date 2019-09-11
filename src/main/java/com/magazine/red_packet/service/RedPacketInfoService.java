package com.magazine.red_packet.service;


import com.baomidou.mybatisplus.service.IService;
import com.magazine.red_packet.entity.RedPacketInfo;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/5
 */
public interface RedPacketInfoService extends IService<RedPacketInfo> {

    /**
     * 新增红包
     *
     * @param uid
     * @param totalNum
     * @param totalAmount
     * @return
     */
    String saveRedPacket(Integer uid, Integer totalNum, Integer totalAmount);

    /**
     * 抢红包
     *
     * @param uid         用户id
     * @param redPacketId 红包id
     * @return
     */
    String getRedPacket(Integer uid, long redPacketId);
}
