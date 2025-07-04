package com.wrp.user.controller;

import java.util.Arrays;

import com.smgi.common.result.Result;
import com.smgi.common.result.ResultUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import com.smgi.user.entity.SysUserEntity;
import com.smgi.user.service.SysUserService;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.web.bind.annotation.*;


/**
 * ${comments}
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Validated
@RestController
@RequestMapping("user/sysuser")
@RequiredArgsConstructor
public class SysUserController {

    private final SysUserService sysUserService;



    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<SysUserEntity> info(@PathVariable("id") Long id){
		SysUserEntity sysUser = sysUserService.getById(id);

        return ResultUtils.success(sysUser);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result<Void> save(@RequestBody SysUserEntity sysUser){
		sysUserService.save(sysUser);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> update(@RequestBody SysUserEntity sysUser){
		sysUserService.updateById(sysUser);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<Void> delete(@RequestBody Long[] ids){
		sysUserService.removeByIds(Arrays.asList(ids));

        return ResultUtils.success();
    }

}
