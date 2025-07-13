package com.wrp.ai.modelparameters.controller;

import dev.langchain4j.model.chat.ChatModel;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/13 8:16
 **/
@RestController
@RequestMapping("model/parameter")
@RequiredArgsConstructor
public class ModelParameterController {

    final ChatModel chatModel;

    @GetMapping("config")
    public String config(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return chatModel.chat(prompt);
    }
}
