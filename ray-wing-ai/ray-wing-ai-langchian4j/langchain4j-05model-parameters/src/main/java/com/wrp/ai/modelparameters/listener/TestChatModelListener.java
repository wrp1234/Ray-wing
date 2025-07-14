package com.wrp.ai.modelparameters.listener;

import dev.langchain4j.model.chat.listener.ChatModelErrorContext;
import dev.langchain4j.model.chat.listener.ChatModelListener;
import dev.langchain4j.model.chat.listener.ChatModelRequestContext;
import dev.langchain4j.model.chat.listener.ChatModelResponseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * {@link https://docs.langchain4j.dev/tutorials/observability#chat-model-observability}
 * @author wrp
 * @since 2025/7/13 8:30
 **/
@Component
@Slf4j
public class TestChatModelListener implements ChatModelListener {

    public static final String TRACE_ID = "TRACE_ID";

    @Override
    public void onRequest(ChatModelRequestContext requestContext) {
        String uuid = UUID.randomUUID().toString();
        requestContext.attributes().put(TRACE_ID, uuid);
        log.info("onRequest: " + requestContext + "\t" + uuid);
    }

    @Override
    public void onResponse(ChatModelResponseContext responseContext) {
        Object o = responseContext.attributes().get(TRACE_ID);
        log.info("onResponse: " + o);
    }

    @Override
    public void onError(ChatModelErrorContext errorContext) {
        log.error("onError: " + errorContext);
    }
}
