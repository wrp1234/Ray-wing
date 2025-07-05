package com.wrp.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.core.domain.BaseEntity;
import com.wrp.user.dict.PermissionType;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	权限
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "public.permission", autoResultMap = true)
public class PermissionEntity extends BaseEntity {

	/**
	 * 权限名称
	 */
	private String permissionName;
	/**
	 * 描述
	 */
	private String description;
	/**
	 * 资源类型
	 */
	private PermissionType type;

}
