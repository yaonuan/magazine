package com.magazine.red_packet.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.magazine.red_packet.entity.RedPacketInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019/9/5
 */
@Mapper
public interface RedPacketInfoMapper extends BaseMapper<RedPacketInfo> {

    /**
     * 根据红包id查询红包信息
     *
     * @param redPacketId 红包id
     * @return
     */
    RedPacketInfo selectByRedPacketId(Long redPacketId);
}
