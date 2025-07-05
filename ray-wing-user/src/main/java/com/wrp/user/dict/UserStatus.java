package com.wrp.user.dict;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import com.wrp.core.domain.BaseEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 *	用户状态
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Getter
@AllArgsConstructor
public enum UserStatus implements BaseEnum {
    ACTIVE(1, "正常"),
    LOCKED(2, "锁定"),
    ;
    @EnumValue
    @JsonValue
    private final int code;
    private final String value;
}
