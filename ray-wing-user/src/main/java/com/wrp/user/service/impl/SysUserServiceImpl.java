package com.wrp.user.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wrp.user.controller.param.LoginUser;
import com.wrp.user.controller.param.RegisterUser;
import com.wrp.user.dict.UserStatus;
import com.wrp.user.entity.SysUserEntity;
import com.wrp.user.exception.UserException;
import com.wrp.user.mapper.SysUserMapper;
import com.wrp.user.service.CaptchaService;
import com.wrp.user.service.SysUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Service
@AllArgsConstructor
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity>
        implements SysUserService {

    private final PasswordEncoder passwordEncoder;
    private final CaptchaService captchaService;

    @Override
    public void login(LoginUser loginUser) {
        if(!captchaService.verify(loginUser.getUuid(), loginUser.getCode())) {
            throw new UserException("验证码错误");
        }
    }

    @Override
    public Long register(RegisterUser registerUser) {
        SysUserEntity user = getByUsername(registerUser.getUsername());
        if(user != null) {
            throw new UserException("用户名已被使用");
        }
        if(getByPhone(registerUser.getPhone()) != null) {
            throw new UserException("电话已被使用");
        }

        user = new SysUserEntity();
        BeanUtils.copyProperties(registerUser, user);
        user.setPassword(passwordEncoder.encode(registerUser.getPassword()));
        user.setStatus(UserStatus.ACTIVE);
        save(user);
        return 0L;
    }

    @Override
    public SysUserEntity getByUsername(String username) {
        return getOne(new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getUsername, username));
    }

    @Override
    public SysUserEntity getByPhone(String phone) {
        return getOne(new LambdaQueryWrapper<SysUserEntity>()
                .eq(SysUserEntity::getPhone, phone));
    }

    // 只需要从数据库中查询到用户信息
    // 密码匹配是框架自动实现了 UsernamePasswordAuthenticationFilter
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        SysUserEntity user = getByUsername(username);
        if(user == null) {
            throw new UserException("用户未注册");
        }

        return User.builder()
                .username(user.getUsername())
                .password(user.getPassword())
                .accountExpired(false)
                .accountLocked(false)
                .credentialsExpired(false)
                .build();
    }
}