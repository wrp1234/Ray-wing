package com.wrp.ai.controller;

import com.wrp.ai.service.Assistant;
import dev.langchain4j.model.chat.StreamingChatModel;
import dev.langchain4j.model.chat.response.ChatResponse;
import dev.langchain4j.model.chat.response.StreamingChatResponseHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

/**
 * @author wrp
 * @since 2025/7/13 10:00
 **/
@RestController
@RequestMapping("chat/streaming")
@RequiredArgsConstructor
public class ChatStreamingController {

    final Assistant assistant;
    final StreamingChatModel streamingChatModel;

    @GetMapping("/lowlevel")
    public Flux<String> chat(@RequestParam(value = "prompt", defaultValue = "成都有什么好吃的") String prompt) {
        return Flux.create(emitter -> {
            streamingChatModel.chat(prompt, new StreamingChatResponseHandler() {
                @Override
                public void onPartialResponse(String partialResponse) {
                    emitter.next(partialResponse);
                }

                @Override
                public void onCompleteResponse(ChatResponse completeResponse) {
                    emitter.complete();
                }

                @Override
                public void onError(Throwable error) {
                    emitter.error(error);
                }
            });
        });
    }

    @GetMapping("/chatstream")
    public void chat2(@RequestParam(value = "prompt", defaultValue = "北京有什么好吃的") String prompt) {
        streamingChatModel.chat(prompt, new StreamingChatResponseHandler() {
            @Override
            public void onPartialResponse(String partialResponse) {
                System.out.println(partialResponse);
            }

            @Override
            public void onCompleteResponse(ChatResponse completeResponse) {
                System.out.println("--response over: " + completeResponse);
            }

            @Override
            public void onError(Throwable error) {
                error.printStackTrace();
            }
        });
    }

    @GetMapping("/assistant")
    public Flux<String> chat3(@RequestParam(value = "prompt", defaultValue = "上海有什么好吃的") String prompt) {
        return assistant.chatStreaming(prompt);
    }
}
