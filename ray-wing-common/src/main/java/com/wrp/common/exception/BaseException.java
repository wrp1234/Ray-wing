package com.wrp.common.exception;

/**
 * 基类异常
 * @author wrp
 * @since 2025年07月04日 21:21
 */
public abstract class BaseException extends RuntimeException {

    public BaseException() {
    }

    public BaseException(String message) {
        super(message);
    }

    public BaseException(String message, Throwable cause) {
        super(message, cause);
    }
}
