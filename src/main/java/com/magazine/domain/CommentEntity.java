package com.magazine.domain;


import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 评论实体类entity
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-25
 */
@Data
public class CommentEntity implements Serializable {

    private Long id;
    private String content;
    private Long userId;
    private String headImg;
    private String name;
    private double point;
    private Integer up;
    private Date createTime;
    private Long orderId;
    private Long videoId;

}
