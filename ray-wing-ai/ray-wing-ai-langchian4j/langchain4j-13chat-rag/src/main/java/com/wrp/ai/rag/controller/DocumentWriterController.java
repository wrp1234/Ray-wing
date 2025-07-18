package com.wrp.ai.rag.controller;

import com.wrp.ai.rag.service.ServerManager;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.io.FileSystemResource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * 文档编写工具
 * @author wrp
 * @since 2025年07月17日 13:31
 **/
@RestController
@RequestMapping("document/writer")
public class DocumentWriterController {

    @jakarta.annotation.Resource
    @Qualifier("qdrantEmbeddingStore")
    private EmbeddingStore<TextSegment> qdrantEmbeddingStore;
    @jakarta.annotation.Resource
    private ServerManager serverManager;



    /**
     * 向量化文档
     */
    @GetMapping("embedding")
    public String embedding(@RequestParam("file") String file) throws Exception {
        Document document = new ApacheTikaDocumentParser().parse(new FileSystemResource(file).getInputStream());
        EmbeddingStoreIngestor.ingest(document, qdrantEmbeddingStore);
        return "ok";
    }

    /**
     * 问答
     */
    @GetMapping("/quest")
    public Flux<String> quest(@RequestParam("prompt") String prompt) {
        return serverManager.chat(prompt);
    }

}
