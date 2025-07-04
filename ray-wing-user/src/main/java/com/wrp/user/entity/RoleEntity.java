package com.wrp.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.smgi.db.domain.BaseEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	${comments}
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys.role", autoResultMap = true)
public class RoleEntity extends BaseEntity {

	/**
	 * $column.comments
	 */
	private String roleName;
	/**
	 * $column.comments
	 */
	private String description;

}
