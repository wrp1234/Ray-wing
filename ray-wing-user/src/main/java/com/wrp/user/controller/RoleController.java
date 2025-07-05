package com.wrp.user.controller;

import com.wrp.core.result.Result;
import com.wrp.user.entity.RoleEntity;
import com.wrp.user.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;


/**
 * 角色接口
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

        return Result.success(role);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result<Void> save(@RequestBody RoleEntity role){
		roleService.save(role);

        return Result.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> update(@RequestBody RoleEntity role){
		roleService.updateById(role);

        return Result.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<Void> delete(@RequestBody Long[] ids){
		roleService.removeByIds(Arrays.asList(ids));

        return Result.success();
    }

}
