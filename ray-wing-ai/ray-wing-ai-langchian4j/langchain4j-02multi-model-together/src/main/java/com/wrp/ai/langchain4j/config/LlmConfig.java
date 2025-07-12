package com.wrp.ai.langchain4j.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025/7/12 21:31
 **/
@Configuration
public class LlmConfig {

    @Bean("qwen")
    public ChatModel chatModelQwen() {
        // OpenAI兼容方式
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("qwen-plus-2025-04-28")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    // https://api-docs.deepseek.com/zh-cn/
    // deepseek两个模型：deepseek-chat   deepseek-reasoner
    @Bean("deepseek")
    public ChatModel chatModelDeepseek() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("DEEPSEEK_API_KEY"))
                .modelName("deepseek-chat")
                // https://api.deepseek.com/v1 兼容OpenAI
                // https://api.deepseek.com 标准
                .baseUrl("https://api.deepseek.com/v1")
                .build();
    }
}
