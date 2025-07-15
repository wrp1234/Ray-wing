package com.wrp.ai.rag.controller;

import com.wrp.ai.rag.service.ChatAssistant;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.apache.tika.ApacheTikaDocumentParser;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

/**
 * @author wrp
 * @since 2025年07月15日 12:08
 **/
@RestController
@RequestMapping("rag")
@RequiredArgsConstructor
public class RagController {

    final ChatAssistant chatAssistant;
    final EmbeddingStore<TextSegment> embeddingStore;

    @GetMapping("add")
    public String testAdd() throws FileNotFoundException {
        // 1. 加载文档
//        Document document = FileSystemDocumentLoader.loadDocument("");
        Document document = new ApacheTikaDocumentParser().parse(new FileInputStream("E:\\document\\王瑞平\\2024年终\\王瑞平2024-考核表.docx"));
        // 2. 转换文档、清洗
        // 3. 拆分文档
        // 4. 文档向量化
        // 5. 导入到向量库
        EmbeddingStoreIngestor.ingest(document, embeddingStore);
        // 6. 检索
        return chatAssistant.chat("介绍一下王瑞平2024年的工作情况");
    }
}
