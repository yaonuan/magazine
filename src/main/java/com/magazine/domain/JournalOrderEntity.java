package com.magazine.domain;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.util.Date;
/**
 * 订单entity
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-25
 */
@TableName("journal_order")
public class JournalOrderEntity implements Serializable {

  private static final long serialVersionUID =1L;
  /**
   * id
   */
  @TableId
  private Integer id;
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
  private Integer journalId;
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
  private Integer userId;
  /**
   * 用户ip地址
   */
  private String ip;
  /**
   * 状态（0表示未删除，1表示已经删除）
   */
  private Integer del;


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


  public String getOutTradeNo() {
    return outTradeNo;
  }

  public void setOutTradeNo(String outTradeNo) {
    this.outTradeNo = outTradeNo;
  }


  public java.util.Date getCreateTime() {
    return createTime;
  }

  public void setCreateTime(java.util.Date createTime) {
    this.createTime = createTime;
  }


  public Integer getTotalFee() {
    return totalFee;
  }

  public void setTotalFee(Integer totalFee) {
    this.totalFee = totalFee;
  }


  public Integer getJournalId() {
    return journalId;
  }

  public void setJournalId(Integer journalId) {
    this.journalId = journalId;
  }


  public String getJournalTitle() {
    return journalTitle;
  }

  public void setJournalTitle(String journalTitle) {
    this.journalTitle = journalTitle;
  }


  public String getJournalImg() {
    return journalImg;
  }

  public void setJournalImg(String journalImg) {
    this.journalImg = journalImg;
  }


  public Integer getUserId() {
    return userId;
  }

  public void setUserId(Integer userId) {
    this.userId = userId;
  }


  public String getIp() {
    return ip;
  }

  public void setIp(String ip) {
    this.ip = ip;
  }


  public Integer getDel() {
    return del;
  }

  public void setDel(Integer del) {
    this.del = del;
  }

}
