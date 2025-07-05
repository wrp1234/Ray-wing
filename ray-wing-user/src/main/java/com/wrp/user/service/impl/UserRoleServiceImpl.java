package com.wrp.user.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wrp.user.mapper.UserRoleMapper;
import com.wrp.user.entity.UserRoleEntity;
import com.wrp.user.service.UserRoleService;

/**
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Service
public class UserRoleServiceImpl extends ServiceImpl<UserRoleMapper, UserRoleEntity>
        implements UserRoleService {

}