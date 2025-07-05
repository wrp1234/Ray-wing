package com.wrp.user.vo;

import lombok.Data;

/**
 * @author wrp
 * @since 2025/7/5 15:15
 **/
@Data
public class CaptchaVo {
    /**
     * 验证码
     */
    private String codeImg;
    /**
     * uuid
     */
    private String uuid;
}
