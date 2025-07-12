package com.wrp.ai.langchain4j.controlller;

import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.output.TokenUsage;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/12 22:42
 **/
@RestController
@RequestMapping("lowapi")
@Slf4j
public class LowApiController {

    @Resource(name = "configChatModel")
    ChatModel chatModel;

    @GetMapping("api01")
    public String api01(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatModel.chat(prompt);
    }

    @GetMapping("api02")
    public String api02(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        ChatResponse response = chatModel.chat(UserMessage.from(prompt));
        AiMessage aiMessage = response.aiMessage();
        TokenUsage tokenUsage = response.tokenUsage();
        log.info("本次调用消耗的token: " + tokenUsage);
        return aiMessage.text();
    }
}
