package com.magazine.domain;


public class JournalOrderEntity {

  private Integer id;
  private String openid;
  private String outTradeNo;
  private java.util.Date createTime;
  private Integer totalFee;
  private Integer journalId;
  private String journalTitle;
  private String journalImg;
  private Integer userId;
  private String ip;
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
