package com.wrp.ai.langchain4j;

import dev.langchain4j.http.client.HttpClientBuilderFactory;
import dev.langchain4j.model.chat.ChatModel;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Map;

/**
 * @author wrp
 * @since 2025/7/12 22:41
 **/
@SpringBootApplication
public class LowHighApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(LowHighApplication.class, args);
        Map<String, ChatModel> beansOfType = context.getBeansOfType(ChatModel.class);
        beansOfType.forEach((k, v) -> {
            System.out.println("名称：" + k + ", 类型：" + v.getClass());
        });
        System.out.println("证明：SpringBoot默认生成的ChatModel信息：名称：openAiChatModel, 类型：class dev.langchain4j.model.openai.OpenAiChatModel");
        System.out.println("=================HttpClientBuilderFactory======================");
        context.getBeansOfType(HttpClientBuilderFactory.class).forEach((k, v) -> {
            System.out.println("名称：" + k + ", 类型：" + v.getClass());
        });
        System.out.println("证明：SpringApplication中没有HttpClientBuilderFactory");
    }
}
