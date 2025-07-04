package com.wrp.core.security;

import com.wrp.common.exception.BaseException;
import com.wrp.core.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author wrp
 * @since 2025年07月04日 21:25
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionControllerAdvice {

    @ExceptionHandler(Exception.class)
    public Result<Void> fallback(Exception e) {
        if(e instanceof BaseException baseException) {
            return Result.error(baseException.getMessage());
        }

        log.error("系统遇到未知的错误", e);
        return Result.error("系统遇到未知的错误");
    }
}
