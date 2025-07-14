package com.wrp.ai.prompt.controller;

import com.wrp.ai.prompt.entity.LawPrompt;
import com.wrp.ai.prompt.service.LawAssistant;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.input.Prompt;
import dev.langchain4j.model.input.PromptTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author wrp
 * @since 2025/7/13 15:08
 **/
@RestController
@RequestMapping("chat/prompt")
@RequiredArgsConstructor
public class ChatPromptController {

    final LawAssistant assistant;
    final ChatModel chatModel;

    @GetMapping("chat")
    public void chat() {
        System.out.println(assistant.chat("介绍一下中国劳动法", 200, "markdown"));;
        System.out.println(assistant.chat("介绍一下红烧鱼的做法", 200, "markdown"));;
        System.out.println(assistant.chat("对比分析一下索尼和尼康两个品牌的相机", 200, "markdown"));;
        System.out.println(assistant.chat("介绍一下四川成都的地形地势", 200, "markdown"));;
    }

    @GetMapping("chat2")
    public void chat2() {
        LawPrompt prompt = new LawPrompt();
        prompt.setLegal("知识产权");
        prompt.setQuestion("TRIPS协议？");
        prompt.setLength(200);
        System.out.println(assistant.chat(prompt));
        prompt = new LawPrompt();
        prompt.setLegal("不动产权");
        prompt.setQuestion("外星人真的存在吗？");
        prompt.setLength(200);
        System.out.println(assistant.chat(prompt));
    }

    @GetMapping("chat3")
    public void chat3() {
        String role = "专业摄影师";
        String question = "人像摄影适合的相机";
        int length = 200;
        // 1. 构造PromptTemplate 模板
        PromptTemplate template = PromptTemplate.from("你是一个{{it}}，用专业知识回答{{question}}的问题，字数限制在{{length}}个字以内");
        // 2. 生成prompt
        Prompt prompt = template.apply(Map.of("it", role, "question", question, "length", length));
        // 3. 生成消息
        UserMessage userMessage = prompt.toUserMessage();
        // 调用AI
        System.out.println(chatModel.chat(userMessage).aiMessage().text());
    }
}
