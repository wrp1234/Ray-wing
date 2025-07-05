package com.wrp.user.dict;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wrp.core.domain.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 权限类型
 * @author wrp
 * @since 2025/7/5 14:07
 **/
@Getter
@AllArgsConstructor
public enum PermissionType implements BaseEnum {
    API(1, "接口"),
    ;

    @EnumValue
    @JsonValue
    private final int code;
    private final String value;
}
