package com.wrp.ai.persistence.service;

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;

/**
 * @author wrp
 * @since 2025/7/13 15:46
 **/
public interface ChatPersistenceAssistant {

    String chat(@MemoryId Long userId, @UserMessage String userMessage);
}
