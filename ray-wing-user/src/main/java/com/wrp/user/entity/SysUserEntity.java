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
@TableName(value = "sys.sys_user", autoResultMap = true)
public class SysUserEntity extends BaseEntity {

	/**
	 * $column.comments
	 */
	private String username;
	/**
	 * $column.comments
	 */
	private String password;
	/**
	 * $column.comments
	 */
	private Integer status;
	/**
	 * $column.comments
	 */
	private String fullName;
	/**
	 * $column.comments
	 */
	private String phone;
	/**
	 * $column.comments
	 */
	private String email;
	/**
	 * $column.comments
	 */
	private String avatarUrl;

}
