package com.wrp.ai.persistence.config;

import com.wrp.ai.persistence.service.ChatPersistenceAssistant;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025/7/13 15:46
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
    public ChatPersistenceAssistant chatPersistenceAssistant(RedisChatMemoryStore chatMemoryStore) {
        return AiServices.builder(ChatPersistenceAssistant.class)
                .chatModel(chatModel())
                // 设置模型记忆提供者
                .chatMemoryProvider(memoryId -> MessageWindowChatMemory.builder()
                        .id(memoryId)
                        .maxMessages(100)
                        // 设置模型记忆的持久化存储
                        .chatMemoryStore(chatMemoryStore)
                        .build())
                .build();
    }
}
