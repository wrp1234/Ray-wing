package com.wrp.ai.rag.service;

import dev.langchain4j.service.SystemMessage;
import reactor.core.publisher.Flux;

/**
 * @author wrp
 * @since 2025年07月17日 13:57
 **/
public interface ServerManager {

    @SystemMessage("你是一个服务器资源专家")
    Flux<String> chat(String prompt);
}
