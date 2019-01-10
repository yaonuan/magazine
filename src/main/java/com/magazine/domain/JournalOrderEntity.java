package com.magazine.domain;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单entity
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-25
 */
@Data
@TableName("journal_order")
public class JournalOrderEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * openid备用
     */
    private String openid;
    /**
     * 订单唯一标识
     */
    private String outTradeNo;
    /**
     * 订单生成时间
     */
    @JsonIgnore
    private Date createTime;
    /**
     * 支付金额，单位分
     */
    private Integer totalFee;
    /**
     * 杂志主键
     */
    private Long journalId;
    /**
     * 杂志名称
     */
    private String journalTitle;
    /**
     * 杂志图片
     */
    private String journalImg;
    /**
     * 用户id
     */
    private Long userId;
    /**
     * 用户ip地址
     */
    private String ip;
    /**
     * 状态（0表示未删除，1表示已经删除）
     */
    private Integer isDeleted;

}
