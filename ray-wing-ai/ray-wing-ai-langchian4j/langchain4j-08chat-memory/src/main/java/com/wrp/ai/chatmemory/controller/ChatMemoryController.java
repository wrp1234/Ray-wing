package com.wrp.ai.chatmemory.controller;

import com.wrp.ai.chatmemory.service.Assistant;
import com.wrp.ai.chatmemory.service.ChatMemoryAssistant;
import jakarta.annotation.Resource;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/13 13:39
 **/
@RestController
@RequestMapping("chatmemory")
@RequiredArgsConstructor
public class ChatMemoryController {

    final Assistant assistant;
    @Resource(name = "chatMemoryAssistantUsingMessageCount")
    ChatMemoryAssistant chatMemoryAssistantUsingMessageCount;
    @Resource(name = "chatMemoryAssistantUsingTokens")
    ChatMemoryAssistant chatMemoryAssistantUsingTokens;

    @GetMapping("test1")
    public void chat() {
        String res1 = assistant.chat("你好，我的名字叫张三");
        System.out.println("res1: " + res1);
        String res2 = assistant.chat("你知道我的名字吗？");
        System.out.println("res2: " + res2);
    }

    @GetMapping("test2")
    public void chat2() {
        chatMemoryAssistantUsingMessageCount.chatWithMemory(1L, "我的名字叫Java");
        String res1 = chatMemoryAssistantUsingMessageCount.chatWithMemory(1L, "你知道我的名字吗？");
        System.out.println("res1: " + res1);

        chatMemoryAssistantUsingMessageCount.chatWithMemory(2L, "我的名字叫C++");
        String res2 = chatMemoryAssistantUsingMessageCount.chatWithMemory(2L, "你知道我的名字吗？");
        System.out.println("res2: " + res2);
    }

    @GetMapping("test3")
    public void chat3() {
        chatMemoryAssistantUsingTokens.chatWithMemory(10L, "我的名字叫Java");
        String res1 = chatMemoryAssistantUsingTokens.chatWithMemory(10L, "你知道我的名字吗？");
        System.out.println("res1: " + res1);

        chatMemoryAssistantUsingTokens.chatWithMemory(20L, "我的名字叫C++");
        String res2 = chatMemoryAssistantUsingTokens.chatWithMemory(20L, "你知道我的名字吗？");
        System.out.println("res2: " + res2);
    }
}
