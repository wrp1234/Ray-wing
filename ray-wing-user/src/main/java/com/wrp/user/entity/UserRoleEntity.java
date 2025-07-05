package com.wrp.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	用户角色关联关系
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys.user_role", autoResultMap = true)
public class UserRoleEntity extends BaseEntity {

	/**
	 * 用户ID
	 */
	private Long userId;
	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 创建者
	 */
	private Long assignedBy;

}
