package com.wrp.ai.langchain4j.controller;

import com.wrp.ai.langchain4j.service.Assistant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/12 22:08
 **/
@RestController
@RequestMapping("highlevel")
@RequiredArgsConstructor
public class HighLevelController {

    final Assistant assistant;

    @GetMapping("/chat")
    public String chat(String message) {
        return assistant.chat(message);
    }
}
