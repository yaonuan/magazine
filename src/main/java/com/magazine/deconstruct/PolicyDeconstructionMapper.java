package com.magazine.deconstruct;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 政策解构
 *
 * @Author : yaonuan
 * @Email : 806039077@qq.com
 * @Date : 2019-04-19
 */
@Mapper
public interface PolicyDeconstructionMapper extends BaseMapper<PolicyDeconstruction> {

	/**
	 * 根据policy_id查询政策实体
	 *
	 * @param policyId
	 * @return
	 */
	PolicyDeconstruction selectByPolicyId(@Param("policyId") Long policyId);

	/**
	 * 根据policy_ids查询政策实体
	 *
	 * @param policyIds
	 * @return
	 */
	List<PolicyDeconstruction> selectByPolicyIds(@Param("policyIds") List<Long> policyIds);

	/**
	 * 根据ids查询存在的id
	 *
	 * @param ids
	 * @return
	 */
	List<Long> selectIdByPolicyIds(@Param("ids") List<Long> ids);

}
