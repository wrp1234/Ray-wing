package com.wrp.user.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smgi.user.mapper.PermissionMapper;
import com.smgi.user.entity.PermissionEntity;
import com.smgi.user.service.PermissionService;

/**
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, PermissionEntity>
        implements PermissionService {

}