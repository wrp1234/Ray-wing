package com.wrp.user.controller;

import com.smgi.common.result.Result;
import com.smgi.common.result.ResultUtils;
import com.smgi.user.entity.ApiResourceEntity;
import com.smgi.user.service.ApiResourceService;
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
@RequestMapping("user/apiresource")
@RequiredArgsConstructor
public class ApiResourceController {
    private final ApiResourceService apiResourceService;



    /**
     * 信息
     */
    @GetMapping("{id}")
    public Result<ApiResourceEntity> info(@PathVariable("id") Long id){
		ApiResourceEntity apiResource = apiResourceService.getById(id);

        return ResultUtils.success(apiResource);
    }

    /**
     * 保存
     */
    @PostMapping
    public Result<Void> save(@RequestBody ApiResourceEntity apiResource){
		apiResourceService.save(apiResource);

        return ResultUtils.success();
    }

    /**
     * 修改
     */
    @PutMapping
    public Result<Void> update(@RequestBody ApiResourceEntity apiResource){
		apiResourceService.updateById(apiResource);

        return ResultUtils.success();
    }

    /**
     * 删除
     */
    @DeleteMapping
    public Result<Void> delete(@RequestBody Long[] ids){
		apiResourceService.removeByIds(Arrays.asList(ids));

        return ResultUtils.success();
    }

}
