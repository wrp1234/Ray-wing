package com.wrp.ai.rag.config;

import com.wrp.ai.rag.service.ChatAssistant;
import com.wrp.ai.rag.service.ServerManager;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiStreamingChatModel;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.service.AiServices;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025年07月15日 12:01
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
    public StreamingChatModel streamingChatModel() {
        return OpenAiStreamingChatModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("qwen-plus-2025-04-28")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return new InMemoryEmbeddingStore<>();
    }

    @Bean
    public ChatAssistant assistant() {
        return AiServices.builder(ChatAssistant.class)
                .chatModel(chatModel())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(50))
                // 内容检索器
                .contentRetriever(EmbeddingStoreContentRetriever.from(embeddingStore()))
                .build();
    }

    @Bean
    public ServerManager serverManager(@Qualifier("qdrantEmbeddingStore") EmbeddingStore<TextSegment> qdrantEmbeddingStore) {
        return AiServices.builder(ServerManager.class)
                .streamingChatModel(streamingChatModel())
                .chatMemory(MessageWindowChatMemory.withMaxMessages(50))
                // 内容检索器
                .contentRetriever(EmbeddingStoreContentRetriever.from(qdrantEmbeddingStore))
                .build();
    }
}
