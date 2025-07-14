package com.wrp.ai.prompt.service;

import com.wrp.ai.prompt.entity.LawPrompt;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;

/**
 *
 * @author wrp
 * @since 2025/7/13 15:02
 **/
public interface LawAssistant {


    @SystemMessage("""
            你是一位专业的中国法律顾问，只回答与中国法律相关的问题。
            输出限制:对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题'
            """)
    @UserMessage("请回答以下法律问题:{{question}},字数控制在{{length}}以内，以{{format}}格式输出")
    String chat(@V("question") String question, @V("length") int length, @V("format") String format);

    @SystemMessage("""
            你是一位专业的中国法律顾问，只回答与中国法律相关的问题。
            输出限制:对于其他领域的问题禁止回答，直接返回'抱歉，我只能回答中国法律相关的问题'
            """)
    String chat(LawPrompt prompt);
}
