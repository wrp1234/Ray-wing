package com.wrp.ai.langchain4j.service;

import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.spring.AiService;

/**
 * Boot整合 高阶api
 * @author wrp
 * @since 2025/7/12 22:07
 **/
// 自动设置
@AiService
// 显示设置
//@AiService(wiringMode = AiServiceWiringMode.EXPLICIT, chatModel = "deepseek")
public interface Assistant {

    @SystemMessage("You are a polite assistant")
    String chat(String userMessage);
}
