package com.wrp.ai.functioncall.controller;

import com.wrp.ai.functioncall.service.FunctionCallAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/13 20:56
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("function/calling")
public class FunctionCallingController {

    final FunctionCallAssistant functionCallAssistant;

    @GetMapping("test1")
    public String test1() {
        String res = functionCallAssistant.chat("开张发票,公司:尚硅谷教育科技有限公司税号:atguigu533 金额:668.12");
        System.out.println("res: " + res);
        return res;
    }
}
