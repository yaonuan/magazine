package com.magazine.red_packet.controller;

import com.magazine.red_packet.service.RedPacketInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/5
 */
@RestController
@RequestMapping("/red_packet")
public class RedPacketController {


    @Autowired
    private RedPacketInfoService packetInfoService;

    @RequestMapping("/add_packet")
    public String saveRedPacket(Integer uid, Integer totalNum, Integer totalAmount) {

        packetInfoService.saveRedPacket(uid,totalNum,totalAmount);
        return "success";
    }

    @RequestMapping("/get_packet")
    public String getRedPacket(Integer uid,Long redPacketId){

        packetInfoService.getRedPacket(uid,redPacketId);
        return null;
    }

}
