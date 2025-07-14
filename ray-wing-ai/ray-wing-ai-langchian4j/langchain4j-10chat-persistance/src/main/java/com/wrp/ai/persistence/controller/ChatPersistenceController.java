package com.wrp.ai.persistence.controller;

import com.wrp.ai.persistence.service.ChatPersistenceAssistant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/13 16:00
 **/
@RestController
@RequestMapping("chat/persistence")
@RequiredArgsConstructor
public class ChatPersistenceController {

    final ChatPersistenceAssistant chatPersistenceAssistant;

    @GetMapping("test")
    public void test() {
        chatPersistenceAssistant.chat(1L, "你好，我的名字是redis");
        System.out.println(chatPersistenceAssistant.chat(1L, "我的名字是什么"));
        chatPersistenceAssistant.chat(2L, "你好，我的名字是nacos");
        System.out.println(chatPersistenceAssistant.chat(2L, "我的名字是什么"));
    }
}
