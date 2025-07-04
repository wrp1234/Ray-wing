package com.wrp.user.to;

import lombok.Data;

/**
 * @author wrp
 * @since 2025年06月30日 16:49
 **/
@Data
public class LoginUser {
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
