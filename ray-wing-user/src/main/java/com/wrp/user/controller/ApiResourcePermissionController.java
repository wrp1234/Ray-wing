package com.wrp.user.controller;

import java.util.Arrays;

import com.smgi.common.result.Result;
import com.smgi.common.result.ResultUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;

import com.smgi.user.entity.ApiResourcePermissionEntity;
import com.smgi.user.service.ApiResourcePermissionService;
import org.springframework.web.bind.annotation.*;


/**
 * ${comments}
 *
 * @author wrp
 * @since 2025-06-30 12:29:49
 */
@Validated
@RestController
@RequestMapping("user/apiresourcepermission")
@RequiredArgsConstructor
public class ApiResourcePermissionController {
    private final ApiResourcePermissionService apiResourcePermissionService;




    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<ApiResourcePermissionEntity> info(@PathVariable("id") Long id){
		ApiResourcePermissionEntity apiResourcePermission = apiResourcePermissionService.getById(id);

        return ResultUtils.success(apiResourcePermission);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result<Void> save(@RequestBody ApiResourcePermissionEntity apiResourcePermission){
		apiResourcePermissionService.save(apiResourcePermission);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> update(@RequestBody ApiResourcePermissionEntity apiResourcePermission){
		apiResourcePermissionService.updateById(apiResourcePermission);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<Void> delete(@RequestBody Long[] ids){
		apiResourcePermissionService.removeByIds(Arrays.asList(ids));

        return ResultUtils.success();
    }

}
