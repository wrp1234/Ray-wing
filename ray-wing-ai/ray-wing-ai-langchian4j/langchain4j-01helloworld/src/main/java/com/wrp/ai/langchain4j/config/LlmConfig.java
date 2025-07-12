package com.wrp.ai.langchain4j.config;

import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @see https://docs.langchain4j.dev/get-started/
 * @author wrp
 * @since 2025/7/12 20:59
 **/
@Configuration
public class LlmConfig {

    @Bean
    public ChatModel chatModelQwen() {
        // OpenAI兼容方式
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("qwen-plus-2025-04-28")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

}
