package com.wrp.user.exception;

import com.wrp.common.exception.BaseException;

/**
 * @author wrp
 * @since 2025/7/5 15:50
 **/
public class CaptchaException extends BaseException {
    public CaptchaException(String message) {
        super(message);
    }
}
