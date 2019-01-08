package com.magazine.domain;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 杂志entity
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-25
 */
@Data
@TableName("journal")
public class JournalEntity implements Serializable {

    private static final long serialVersionUID = 1L;
    /**
     * id
     */
    @TableId
    private Long id;
    /**
     * 标题
     */
    private String title;
    /**
     * 概述
     */
    private String summary;
    /**
     * 封面图
     */
    private String coverImg;
    /**
     * 观看数
     */
    private Integer viewNum;
    /**
     * 价格,分
     */
    private Integer price;
    /**
     * 创建时间
     */
    @JsonIgnore
    private Date createTime;
    /**
     * 0表示未上线，1表示上线
     */
    private Integer online;
    /**
     * (暂不开发)默认8.7，最高10分
     */
    private double point;
    /**
     * 是否删除（0，未删除；1，已删除）
     */
    @JsonIgnore
    private Integer isDeleted;

}
