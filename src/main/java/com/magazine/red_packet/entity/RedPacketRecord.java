package com.magazine.red_packet.entity;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.data.annotation.Id;

import java.io.Serializable;
import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = true)
@TableName("red_packet_record")
public class RedPacketRecord extends Model<RedPacketRecord> implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;
    /**
     * 抢到红包的金额
     */
    private Integer amount;
    /**
     * 抢到红包的用户的用户名
     */
    private String nickName;
    /**
     * 抢到红包的用户的头像
     */
    private String imgUrl;
    /**
     * 抢到红包用户的用户标识
     */
    private Integer uid;
    /**
     * 红包id，采用timestamp+5位随机数
     */
    private Long redPacketId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 更新时间
     */
    private Date updateTime;


    @Override
    protected Serializable pkVal() {
        return this.id;
    }
}
