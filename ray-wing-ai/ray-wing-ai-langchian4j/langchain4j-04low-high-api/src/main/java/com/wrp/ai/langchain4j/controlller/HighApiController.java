package com.wrp.ai.langchain4j.controlller;

import com.wrp.ai.langchain4j.service.Assistant;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author wrp
 * @since 2025/7/12 23:10
 **/
@RestController
@RequestMapping("highapi")
@RequiredArgsConstructor
public class HighApiController {

    final Assistant assistant;

    @GetMapping("q")
    public String highApi(@RequestParam(value = "prompt", defaultValue = "你是谁") String prompt) {
        return assistant.chat(prompt);
    }
}
