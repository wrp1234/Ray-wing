package com.wrp.ai.image.controller;

import dev.langchain4j.data.message.ImageContent;
import dev.langchain4j.data.message.TextContent;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Base64;

/**
 * @author wrp
 * @since 2025/7/13 8:55
 **/
@RestController
@RequestMapping("chat/image")
@RequiredArgsConstructor
public class ChatImageController {

    final ChatModel chatModel;

    @Value("classpath:static/test.png")
    Resource resource;

    @GetMapping("call")
    public String call(@RequestParam(value = "prompt",
            defaultValue = "从下面图片中获取来源网站名称，沪深300基金的走势，以及判断其最低点出现在几月份")
                           String prompt) throws IOException {
        // 1. 图片转base64
        byte[] byteArray = resource.getContentAsByteArray();
        String base64 = Base64.getEncoder().encodeToString(byteArray);

        // 2. 提示词指定
        UserMessage userMessage = UserMessage.from(TextContent.from(prompt),
                ImageContent.from(base64, MediaType.IMAGE_PNG_VALUE));

        // 3. 调用模型
        ChatResponse response = chatModel.chat(userMessage);

        // 4. 解析与输出
        return response.aiMessage().text();
    }
}
