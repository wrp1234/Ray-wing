package com.wrp.core.security;

import com.wrp.common.exception.BaseException;
import com.wrp.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

/**
 * @author wrp
 * @since 2025年07月04日 21:25
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public Result<Void> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return Result.error(e.getLocalizedMessage());
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public Result<Void> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        return Result.error("参数格式不正确");
    }

    @ExceptionHandler(Exception.class)
    public Result<Void> fallback(Exception e) {
        if(e instanceof BaseException baseException) {
            return Result.error(baseException.getMessage());
        }

        log.error("系统遇到未知的错误", e);
        return Result.error("系统遇到未知的错误");
    }
}
