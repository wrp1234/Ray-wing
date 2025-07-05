package com.wrp.user.controller.param;

import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

/**
 * 登录用户
 * @author wrp
 * @since 2025/7/5 14:24
 **/
@Data
public class LoginUser {
    /**
     * 用户名
     */
    @NotEmpty(message = "请填写用户名")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "请填写密码")
    private String password;
    /**
     * 验证码
     */
    @NotEmpty(message = "请填写验证码")
    private String code;
    /**
     * 唯一id
     */
    @NotEmpty(message = "uuid为空")
    private String uuid;
}
