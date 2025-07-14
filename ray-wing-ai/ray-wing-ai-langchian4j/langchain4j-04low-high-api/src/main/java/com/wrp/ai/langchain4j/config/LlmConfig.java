package com.wrp.ai.langchain4j.config;

import com.wrp.ai.langchain4j.service.Assistant;
import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilderFactory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025/7/12 22:41
 **/
@Configuration
public class LlmConfig {

    @Bean("configChatModel")
    public ChatModel configChatModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("qwen-plus-2025-04-28")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                // 解决 Conflict: multiple HTTP clients have been found in the classpath: [dev.langchain4j.http.client.jdk.JdkHttpClientBuilderFactory, dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilderFactory]. Please explicitly specify the one you wish to use.
                .httpClientBuilder(new SpringRestClientBuilderFactory().create())
                .build();
    }

    // https://docs.langchain4j.dev/tutorials/ai-services#how-does-it-work
    // 通过AiServices方式创建Assistant
    // openAiChatModel 是springboot自动注册的ChatModel名称
    @Bean
    public Assistant assistant(@Qualifier("openAiChatModel") ChatModel chatModel) {
        return AiServices.create(Assistant.class, chatModel);
    }
}
