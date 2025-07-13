package com.wrp.ai.chatmemory.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 * @author wrp
 * @since 2025/7/13 13:19
 **/
public interface ChatMemoryAssistant {

    /**
     * 带记忆的聊天
     */
    String chatWithMemory(@MemoryId Long userId, @UserMessage String prompt);
}
