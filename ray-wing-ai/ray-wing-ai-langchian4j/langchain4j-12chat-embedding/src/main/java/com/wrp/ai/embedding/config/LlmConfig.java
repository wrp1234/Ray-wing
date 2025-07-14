package com.wrp.ai.embedding.config;

import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.model.openai.OpenAiEmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025年07月14日 13:34
 **/
@Configuration
public class LlmConfig {

    @Bean
    public EmbeddingModel chatModel() {
        return OpenAiEmbeddingModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("text-embedding-v4")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    /**
     * 创建Qdrant客户端
     */
    @Bean
    public QdrantClient qdrantClient() {
        return new QdrantClient(QdrantGrpcClient
                .newBuilder("127.0.0.1", 6334, false)
                .build()
        );
    }

    /**
     * 创建向量存储
     */
    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        return QdrantEmbeddingStore.builder()
                .host("127.0.0.1")
                .port(6334)
                .collectionName("test-qdrant")
                .build();
    }
}
