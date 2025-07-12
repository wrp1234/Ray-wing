package com.wrp.ai.langchain4j.controller;

import dev.langchain4j.model.chat.ChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/12 21:05
 **/
@RestController
@RequiredArgsConstructor
@RequestMapping("hello")
public class HelloLangChain4jController {

    final ChatModel chatModel;

    @GetMapping("langchain4j/qwen")
    public String helloWorld(@RequestParam(value = "question", defaultValue = "你是谁") String question) {
        return chatModel.chat(question);
    }
}
