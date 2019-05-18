package com.magazine.deconstruct;

import com.baomidou.mybatisplus.activerecord.Model;
import com.baomidou.mybatisplus.annotations.TableName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

/**
 * 政策解构实体类
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@TableName("policy_deconstruction")
public class PolicyDeconstruction extends Model<PolicyDeconstruction> implements Serializable  {

	private static final long serialVersionUID = 1L;

	/**
	 * 政策id
	 */
	private Long policyId;

	/**
	 * 政策标题
	 */
	private String policyTitle;
	/**
	 * 动词序列化
	 */
	private byte[] verbs;


	@Override
	protected Serializable pkVal() {
		return this.policyId;
	}
}
