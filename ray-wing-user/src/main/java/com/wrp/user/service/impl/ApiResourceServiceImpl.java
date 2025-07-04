package com.wrp.user.service.impl;

import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.smgi.user.mapper.ApiResourceMapper;
import com.smgi.user.entity.ApiResourceEntity;
import com.smgi.user.service.ApiResourceService;

/**
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Service
public class ApiResourceServiceImpl extends ServiceImpl<ApiResourceMapper, ApiResourceEntity>
        implements ApiResourceService {

}