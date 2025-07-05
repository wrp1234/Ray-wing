package com.wrp.user.controller.param;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

/**
 * 用户注册信息
 * @author wrp
 * @since 2025/7/5 14:28
 **/
@Data
public class RegisterUser {

    /**
     * 用户名
     */
    @NotEmpty(message = "请填写用户名")
    private String username;
    /**
     * 密码
     */
    @NotEmpty(message = "请填写密码")
    @Pattern(regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$",
            message = "密码必须包含大写字母，小写字母和数字，至少8个字符")
    private String password;
    /**
     * 完整名称
     */
    @NotEmpty(message = "请填写完整名称")
    private String fullName;
    /**
     * 电话
     */
    @NotEmpty(message = "请填写电话号码")
    @Pattern(regexp = "^1(3[0-9]|4[01456879]|5[0-35-9]|6[2567]|7[0-8]|8[0-9]|9[0-35-9])\\d{8}$",
            message = "请填写真实的电话号码")
    private String phone;
    /**
     * 邮箱
     */
    @NotEmpty(message = "请填写邮箱")
    @Email(message = "请填写真实的邮箱")
    private String email;
    /**
     * 头像
     */
    private Long avatar;
}
