package com.wrp.user.controller;

import com.smgi.common.result.Result;
import com.smgi.common.result.ResultUtils;
import com.smgi.user.entity.RoleEntity;
import com.smgi.user.service.RoleService;
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
@RequestMapping("user/role")
@RequiredArgsConstructor
public class RoleController {

    private final RoleService roleService;


    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<RoleEntity> info(@PathVariable("id") Long id){
		RoleEntity role = roleService.getById(id);

        return ResultUtils.success(role);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result<Void> save(@RequestBody RoleEntity role){
		roleService.save(role);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> update(@RequestBody RoleEntity role){
		roleService.updateById(role);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<Void> delete(@RequestBody Long[] ids){
		roleService.removeByIds(Arrays.asList(ids));

        return ResultUtils.success();
    }

}
