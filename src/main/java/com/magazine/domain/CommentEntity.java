package com.magazine.domain;


import lombok.Data;

import java.io.Serializable;

/**
 * 评论实体类entity
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-25
 */
@Data
public class CommentEntity implements Serializable {

  private Integer id;
  private String content;
  private Integer userId;
  private String headImg;
  private String name;
  private double point;
  private Integer up;
  private java.util.Date createTime;
  private Integer orderId;
  private Integer videoId;

}
