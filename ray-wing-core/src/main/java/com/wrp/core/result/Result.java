package com.wrp.core.result;

import com.wrp.core.dict.ResultCode;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 统一返回结果
 * @author wrp
 * @since 2025年07月04日 20:33
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result <T> {
    private int code;
    private String message;
    private T data;

    /**
     * 返回成功，无数据
     */
    public static Result<Void> success() {
        return success(null);
    }

    /**
     * 返回成功，带数据
     */
    public static <R> Result<R> success(R data) {
        return of(ResultCode.SUCCESS, data);
    }

    /**
     * 返回失败，没有数据
     */
    public static Result<Void> error() {
        return of(ResultCode.ERROR, null);
    }

    /**
     * 返回失败，没有数据
     */
    public static Result<Void> error(String message) {
        return new Result<>(ResultCode.ERROR.getCode(), message, null);
    }

    private static <R> Result<R> of(ResultCode code, R data) {
        return new Result<>(code.getCode(), code.getValue(), data);
    }
}
