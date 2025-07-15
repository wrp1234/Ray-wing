package com.wrp.ai.mcp.service;

import reactor.core.publisher.Flux;

/**
 * @author wrp
 * @since 2025年07月15日 14:04
 **/
public interface McpService {

    Flux<String> chat(String prompt);
}
