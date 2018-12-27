package com.magazine.domain;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * 用户entity
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-23
 */
@TableName("user")
public class UserEntity implements Serializable {

  /**
   * id
   */
  @TableId
  private Integer id;
  /**
   * 备用
   */
  private String openid;
  /**
   * 昵称
   */
  private String name;
  /**
   * 用户登录的密码
   */
  private String password;
  /**
   * 头像
   */
  private String headImg;
  /**
   * 手机号
   */
  private String phone;
  /**
   * 用户签名
   */
  private String sign;
  /**
   * 0表示女，1表示男
   */
  private Integer sex;
  /**
   * 城市
   */
  private String city;
  /**
   * 创建时间
   */
  @JsonIgnore
  private Date createTime;


  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }


  public String getOpenid() {
    return openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }


  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }


  public String getHeadImg() {
    return headImg;
  }

  public void setHeadImg(String headImg) {
    this.headImg = headImg;
  }


  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }


  public String getSign() {
    return sign;
  }

  public void setSign(String sign) {
    this.sign = sign;
  }


  public Integer getSex() {
    return sex;
  }

  public void setSex(Integer sex) {
    this.sex = sex;
  }


  public String getCity() {
    return city;
  }

  public void setCity(String city) {
    this.city = city;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }

}
