package com.wrp.user.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wrp.user.controller.param.LoginUser;
import com.wrp.user.controller.param.RegisterUser;
import com.wrp.user.entity.SysUserEntity;

/**
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
//public interface SysUserService extends IService<SysUserEntity>, UserDetailsService {
public interface SysUserService extends IService<SysUserEntity> {

    /**
     * 登录
     */
    void login(LoginUser loginUser);

    /**
     * 注册用户
     */
    Long register(RegisterUser registerUser);

    SysUserEntity getByUsername(String username);

    SysUserEntity getByPhone(String phone);
}

