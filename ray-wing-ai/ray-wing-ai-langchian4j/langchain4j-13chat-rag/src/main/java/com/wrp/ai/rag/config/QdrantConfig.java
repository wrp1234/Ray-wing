package com.wrp.ai.rag.config;

import com.google.common.util.concurrent.ListenableFuture;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.qdrant.QdrantEmbeddingStore;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.QdrantGrpcClient;
import io.qdrant.client.grpc.Collections;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.concurrent.ExecutionException;

/**
 * @author wrp
 * @since 2025年07月17日 14:13
 **/
@Configuration
public class QdrantConfig {

    public static final String collectionName = "document-qdrant";

    /**
     * 创建Qdrant客户端
     */
    @Bean
    public QdrantClient qdrantClient() throws ExecutionException, InterruptedException {
        QdrantClient qdrantClient = new QdrantClient(QdrantGrpcClient
                .newBuilder("127.0.0.1", 6334, false)
                .build()
        );

        ListenableFuture<Boolean> future = qdrantClient.collectionExistsAsync(QdrantConfig.collectionName);
        if(!future.get()) {
            System.out.println("创建Collection");
            qdrantClient.createCollectionAsync(QdrantConfig.collectionName, Collections.VectorParams.newBuilder()
                    .setDistance(Collections.Distance.Cosine)
                    // 向量大小
                    .setSize(384)
                    .build());
        }
        return qdrantClient;
    }

    /**
     * 创建向量存储
     */
    @Bean
    @Primary
    public EmbeddingStore<TextSegment> qdrantEmbeddingStore() {

        return QdrantEmbeddingStore.builder()
                .host("127.0.0.1")
                .port(6334)
                .collectionName(collectionName)
                .build();
    }
}
