package com.magazine.domain;


import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户entity
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2018-12-23
 */
@Data
@TableName("user")
public class UserEntity implements Serializable {

    /**
     * id
     */
    @TableId
    private Long id;
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

}
