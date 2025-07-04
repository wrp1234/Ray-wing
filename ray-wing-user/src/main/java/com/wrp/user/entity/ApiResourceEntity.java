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
@TableName(value = "sys.api_resource", autoResultMap = true)
public class ApiResourceEntity extends BaseEntity {

	/**
	 * $column.comments
	 */
	private String resourcePath;
	/**
	 * $column.comments
	 */
	private String httpMethod;
	/**
	 * $column.comments
	 */
	private String resourceName;
	/**
	 * $column.comments
	 */
	private String description;

}
