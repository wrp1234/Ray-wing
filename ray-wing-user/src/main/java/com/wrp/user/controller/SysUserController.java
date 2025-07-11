package com.wrp.user.controller;

import com.wrp.core.result.Result;
import com.wrp.user.controller.param.LoginUser;
import com.wrp.user.controller.param.RegisterUser;
import com.wrp.user.entity.SysUserEntity;
import com.wrp.user.exception.UserException;
import com.wrp.user.service.SysUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
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
     * 注销登录
     */
    @GetMapping("logout/{id}")
    public Result<Void> logout(@PathVariable Long id) {
        return Result.success();
    }

    /**
     * 查询用户列表
     */
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("list")
    public Result<List<SysUserEntity>> list() {
        return Result.success(sysUserService.list());
    }

    /**
     * 信息
     */
    @GetMapping("info/{id}")
    public Result<SysUserEntity> info(@PathVariable("id") Long id){
        Optional<SysUserEntity> optById = sysUserService.getOptById(id);
        if(optById.isEmpty()){
            throw new UserException("用户不存在");
        }
        return Result.success(optById.get());
    }
}
