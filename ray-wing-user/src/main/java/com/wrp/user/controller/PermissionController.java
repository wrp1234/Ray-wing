package com.wrp.user.controller;

import com.smgi.common.result.Result;
import com.smgi.common.result.ResultUtils;
import com.smgi.user.entity.PermissionEntity;
import com.smgi.user.service.PermissionService;
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
@RequestMapping("user/permission")
@RequiredArgsConstructor
public class PermissionController {

    private final PermissionService permissionService;




    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<PermissionEntity> info(@PathVariable("id") Long id){
		PermissionEntity permission = permissionService.getById(id);

        return ResultUtils.success(permission);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result<Void> save(@RequestBody PermissionEntity permission){
		permissionService.save(permission);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> update(@RequestBody PermissionEntity permission){
		permissionService.updateById(permission);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<Void> delete(@RequestBody Long[] ids){
		permissionService.removeByIds(Arrays.asList(ids));

        return ResultUtils.success();
    }

}
