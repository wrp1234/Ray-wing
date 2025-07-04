package com.wrp.user.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smgi.user.mapper.SysUserMapper;
import com.smgi.user.entity.SysUserEntity;
import com.smgi.user.service.SysUserService;

/**
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUserEntity>
        implements SysUserService {

}