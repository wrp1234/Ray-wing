package com.wrp.core.domain;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author wrp
 * @since 2025年07月04日 21:20
 */
@Data
public class PageParam<T> {

    /**
     * 页码
     */
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码最小为1")
    private Long pageNo;
    /**
     * 分页大小
     */
    @NotNull(message = "分页大小不能为空")
    @Min(value = 5, message = "分页大小最小为5")
    @Max(value = 100, message = "分页大小最大为100")
    private Long pageSize;
    @Valid
    @NotNull(message = "分页参数不全")
    private T param;

    public PageParam() {
    }

    public PageParam(Long pageNo, Long pageSize) {
        this.pageNo = pageNo;
        this.pageSize = pageSize;
    }
}
