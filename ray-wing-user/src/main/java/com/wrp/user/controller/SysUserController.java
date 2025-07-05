package com.wrp.user.controller;

import com.wrp.core.result.Result;
import com.wrp.user.controller.param.LoginUser;
import com.wrp.user.controller.param.RegisterUser;
import com.wrp.user.entity.SysUserEntity;
import com.wrp.user.exception.UserException;
import com.wrp.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

/**
 * 用户接口
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Validated
@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;

    /**
     * 用户名密码登录
     */
    @PostMapping("login")
    public Result<Void> login(@RequestBody @Validated LoginUser loginUser) {
        sysUserService.login(loginUser);
        return Result.success();
    }

    /**
     * 注册用户
     */
    @PostMapping("register")
    public Result<Long> register(@RequestBody @Validated RegisterUser registerUser) {
        return Result.success(sysUserService.register(registerUser));
    }

    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<SysUserEntity> info(@PathVariable("id") Long id){
        Optional<SysUserEntity> optById = sysUserService.getOptById(id);
        if(optById.isEmpty()){
            throw new UserException("用户不存在");
        }
        return Result.success(optById.get());
    }
}
