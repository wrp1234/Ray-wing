package com.wrp.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	角色-权限关联信息
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys.role_permission", autoResultMap = true)
public class RolePermissionEntity extends BaseEntity {

	/**
	 * 角色ID
	 */
	private Long roleId;
	/**
	 * 权限ID
	 */
	private Long permissionId;
	/**
	 * 创建者
	 */
	private Long assignedBy;

}
