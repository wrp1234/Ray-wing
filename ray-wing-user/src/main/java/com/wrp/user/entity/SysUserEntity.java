package com.wrp.user.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.wrp.core.domain.BaseEntity;
import com.wrp.user.dict.UserStatus;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 *	用户信息
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
@TableName(value = "sys.sys_user", autoResultMap = true)
public class SysUserEntity extends BaseEntity {

	/**
	 * 用户名
	 */
	private String username;
	/**
	 * 密码
	 */
	private String password;
	/**
	 * 状态
	 */
	private UserStatus status;
	/**
	 * 完整名称
	 */
	private String fullName;
	/**
	 * 电话
	 */
	private String phone;
	/**
	 * 邮箱
	 */
	private String email;
	/**
	 * 头像
	 */
	private Long avatar;

}
