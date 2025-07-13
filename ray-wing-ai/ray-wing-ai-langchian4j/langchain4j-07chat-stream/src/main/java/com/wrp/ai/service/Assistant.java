package com.wrp.ai.service;

import reactor.core.publisher.Flux;

/**
 * @author wrp
 * @since 2025/7/13 9:56
 **/
public interface Assistant {

    String chat(String prompt);

    Flux<String> chatStreaming(String prompt);
}
