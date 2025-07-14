package com.wrp.ai.persistence.config;

import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * {@link https://docs.langchain4j.dev/tutorials/chat-memory#persistence}
 * 序列化和反序列化工具类 {@link ChatMessageDeserializer} 、 {@link  ChatMessageSerializer}
 * @author wrp
 * @since 2025/7/13 15:50
 **/
@Component
@RequiredArgsConstructor
public class RedisChatMemoryStore implements ChatMemoryStore {

    public static final String CHAT_MEMORY_STORE_NAME = "chat-memory-store:";

    final RedisTemplate<String, String> redisTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        String json = redisTemplate.opsForValue().get(CHAT_MEMORY_STORE_NAME + memoryId);
        return ChatMessageDeserializer.messagesFromJson(json);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> messages) {
        redisTemplate.opsForValue().set(CHAT_MEMORY_STORE_NAME + memoryId, ChatMessageSerializer.messagesToJson(messages));
    }

    @Override
    public void deleteMessages(Object memoryId) {
        redisTemplate.delete(CHAT_MEMORY_STORE_NAME + memoryId);
    }
}
