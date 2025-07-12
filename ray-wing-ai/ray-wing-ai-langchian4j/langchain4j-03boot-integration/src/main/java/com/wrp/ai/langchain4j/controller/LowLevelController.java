package com.wrp.ai.langchain4j.controller;

import dev.langchain4j.model.chat.ChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * boot整合低阶api
 * @author wrp
 * @since 2025/7/12 22:06
 **/
@RestController
@RequestMapping("lowlevel")
@RequiredArgsConstructor
public class LowLevelController {

    final ChatModel chatModel;

    @GetMapping("/chat")
    public String model(@RequestParam(value = "message", defaultValue = "Hello") String message) {
        return chatModel.chat(message);
    }
}
