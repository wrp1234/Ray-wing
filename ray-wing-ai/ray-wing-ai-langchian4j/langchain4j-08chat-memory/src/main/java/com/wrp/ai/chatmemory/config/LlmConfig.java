package com.wrp.ai.chatmemory.config;

import com.wrp.ai.chatmemory.service.Assistant;
import com.wrp.ai.chatmemory.service.ChatMemoryAssistant;
import dev.langchain4j.memory.ChatMemory;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.memory.chat.TokenWindowChatMemory;
import dev.langchain4j.model.TokenCountEstimator;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiTokenCountEstimator;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025/7/13 13:16
 **/
@Configuration
public class LlmConfig {

    @Bean
    public ChatModel chatModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("qwen-long-latest")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public Assistant assistant() {
        return AiServices.create(Assistant.class, chatModel());
    }

    @Bean(name = "chatMemoryAssistantUsingMessageCount")
    public ChatMemoryAssistant chatMemoryAssistant() {
        return AiServices.builder(ChatMemoryAssistant.class)
                .chatModel(chatModel())
                // 设置记忆
                // 共享记忆，只创建一个记忆
//                .chatMemory(chatMemory())
                // provider可以隔离记忆，根据不同的memoryId创建不同的记忆
                .chatMemoryProvider(memoryId-> chatMemory())
                .build();
    }

    @Bean
    public ChatMemory chatMemory() {
        return MessageWindowChatMemory.withMaxMessages(100);
    }

    @Bean(name = "chatMemoryAssistantUsingTokens")
    public ChatMemoryAssistant chatMemoryAssistantUsingTokens() {
        return AiServices.builder(ChatMemoryAssistant.class)
                .chatModel(chatModel())
                // 设置记忆
                // 共享记忆
//                .chatMemory(chatMemory())
                // provider可以隔离记忆
                .chatMemoryProvider(memoryId->
                        TokenWindowChatMemory.withMaxTokens(3000, tokenCountEstimator()))
                .build();
    }


    @Bean
    public TokenCountEstimator tokenCountEstimator(){
        // gpt-4. 免费使用
        return new OpenAiTokenCountEstimator("gpt-4.");
    }
}
