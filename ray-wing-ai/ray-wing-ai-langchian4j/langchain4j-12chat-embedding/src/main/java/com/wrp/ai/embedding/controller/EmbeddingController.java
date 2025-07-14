package com.wrp.ai.embedding.controller;

import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.filter.MetadataFilterBuilder;
import io.qdrant.client.QdrantClient;
import io.qdrant.client.grpc.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.langchain4j.store.embedding.filter.MetadataFilterBuilder.metadataKey;

/**
 * @author wrp
 * @since 2025年07月14日 13:41
 **/
@RestController
@RequestMapping("embedding")
@RequiredArgsConstructor
public class EmbeddingController {

    final EmbeddingModel embeddingModel;
    final QdrantClient qdrantClient;
    final EmbeddingStore<TextSegment> embeddingStore;


    /**
     * 文本向量化
     */
    @GetMapping("embed")
    public String embedding() {
        String prompt = """
                静夜思
                床前明月光，疑是地上霜。
                举头望明月，低头思故乡。
                """;
        Response<Embedding> embed = embeddingModel.embed(prompt);
        return embed.content().toString();
    }

    /**
     * 创建qdrant集合
     */
    @GetMapping("create/collection")
    public void createCollection() {
        qdrantClient.createCollectionAsync("test-qdrant", Collections.VectorParams.newBuilder()
                        .setDistance(Collections.Distance.Cosine)
                        .setSize(1024)
                .build());
    }

    /**
     * 将文本向量化结果存到qdrant
     */
    @GetMapping("add")
    public String add() {
        String prompt = """
                静夜思
                床前明月光，疑是地上霜。
                举头望明月，低头思故乡。
                """;
        TextSegment segment = TextSegment.from(prompt);
        segment.metadata().put("author", "唐·李白");
        // 文本向量化
        Embedding content = embeddingModel.embed(segment).content();
        // 将向量化结果存储到qdrant
        return embeddingStore.add(content, segment);
    }

    @GetMapping("query1")
    public void query1() {
        Embedding content = embeddingModel.embed("静夜思说的是什么").content();
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(content)
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> result = embeddingStore.search(searchRequest);
        System.out.println(result.matches().get(0).embedded().text());
    }

    /**
     * 有过滤条件查询
     */
    @GetMapping("query2")
    public void query2() {
        Embedding content = embeddingModel.embed("静夜思").content();
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(content)
                .filter(MetadataFilterBuilder.metadataKey("author").isEqualTo("唐·李白"))
                .maxResults(1)
                .build();
        EmbeddingSearchResult<TextSegment> result = embeddingStore.search(searchRequest);
        System.out.println(result.matches().get(0).embedded().text());
    }

}
