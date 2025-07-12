package com.wrp.ai.langchain4j.controller;

import dev.langchain4j.model.chat.ChatModel;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/12 21:33
 **/
@RestController
@RequestMapping("multimodel")
public class MultiModelController {

    @Resource(name = "qwen")
    ChatModel chatModelQwen;

    @Resource(name = "deepseek")
    ChatModel chatModelDeepseek;

    @GetMapping("qwen/call")
    public String qwenCall(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatModelQwen.chat(prompt);
    }

    @GetMapping("deepseek/call")
    public String deepseekCall(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatModelDeepseek.chat(prompt);
    }
}
