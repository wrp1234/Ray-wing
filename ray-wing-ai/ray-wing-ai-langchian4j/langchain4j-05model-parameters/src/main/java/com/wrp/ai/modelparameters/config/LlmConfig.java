package com.wrp.ai.modelparameters.config;

import dev.langchain4j.http.client.spring.restclient.SpringRestClientBuilderFactory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.openai.OpenAiChatModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;
import java.util.List;

/**
 * @author wrp
 * @since 2025/7/13 8:14
 **/
@Configuration
public class LlmConfig {

    @Autowired
    List<ChatModelListener> listeners;

    @Bean
    public ChatModel chatModelQwen() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("qwen-plus-2025-04-28")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .httpClientBuilder(new SpringRestClientBuilderFactory().create())
                // 日志
                .logRequests(true)
                .logResponses(true)
                // 监听器
                .listeners(listeners)
                // 最大重试次数，默认3次
                .maxRetries(2)
                // 超时时间
                .timeout(Duration.ofSeconds(10))
                .build();
    }
}
