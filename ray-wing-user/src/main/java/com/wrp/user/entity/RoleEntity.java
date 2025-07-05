package com.wrp.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.core.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	角色
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "public.role", autoResultMap = true)
public class RoleEntity extends BaseEntity {

	/**
	 * 角色名称
	 */
	private String roleName;
	/**
	 * 描述
	 */
	private String description;

}
