package com.wrp.user.controller;

import com.smgi.common.result.Result;
import com.smgi.common.result.ResultUtils;
import com.smgi.user.entity.RolePermissionEntity;
import com.smgi.user.service.RolePermissionService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * ${comments}
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Validated
@RestController
@RequestMapping("user/rolepermission")
@RequiredArgsConstructor
public class RolePermissionController {

    private final RolePermissionService rolePermissionService;


    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<RolePermissionEntity> info(@PathVariable("id") Long id){
		RolePermissionEntity rolePermission = rolePermissionService.getById(id);

        return ResultUtils.success(rolePermission);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result<Void> save(@RequestBody RolePermissionEntity rolePermission){
		rolePermissionService.save(rolePermission);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> update(@RequestBody RolePermissionEntity rolePermission){
		rolePermissionService.updateById(rolePermission);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<Void> delete(@RequestBody Long[] ids){
		rolePermissionService.removeByIds(Arrays.asList(ids));

        return ResultUtils.success();
    }

}
