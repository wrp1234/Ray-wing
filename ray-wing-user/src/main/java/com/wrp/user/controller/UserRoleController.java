package com.wrp.user.controller;

import com.smgi.common.result.Result;
import com.smgi.common.result.ResultUtils;
import com.smgi.user.entity.UserRoleEntity;
import com.smgi.user.service.UserRoleService;
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
@RequestMapping("user/userrole")
@RequiredArgsConstructor
public class UserRoleController {

    private final UserRoleService userRoleService;


    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<UserRoleEntity> info(@PathVariable("id") Long id){
		UserRoleEntity userRole = userRoleService.getById(id);

        return ResultUtils.success(userRole);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result<Void> save(@RequestBody UserRoleEntity userRole){
		userRoleService.save(userRole);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> update(@RequestBody UserRoleEntity userRole){
		userRoleService.updateById(userRole);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<Void> delete(@RequestBody Long[] ids){
		userRoleService.removeByIds(Arrays.asList(ids));

        return ResultUtils.success();
    }

}
