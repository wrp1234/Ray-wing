package com.wrp.ai.prompt.config;

import com.wrp.ai.prompt.service.LawAssistant;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025/7/13 15:00
 **/
@Configuration
public class LlmConfig {

    @Bean
    public ChatModel chatModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("qwen-plus-2025-04-28")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public LawAssistant lawAssistant() {
        return AiServices.create(LawAssistant.class, chatModel());
    }
}
