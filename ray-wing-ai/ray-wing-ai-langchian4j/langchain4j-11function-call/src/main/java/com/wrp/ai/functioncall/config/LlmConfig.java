package com.wrp.ai.functioncall.config;

import com.wrp.ai.functioncall.service.FunctionCallAssistant;
import com.wrp.ai.functioncall.service.InvoiceHandler;
import dev.langchain4j.model.chat.ChatModel;
import dev.langchain4j.model.openai.OpenAiChatModel;
import dev.langchain4j.service.AiServices;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025/7/13 20:45
 **/
@Configuration
public class LlmConfig {

    @Bean
    public ChatModel chatModel() {
        return OpenAiChatModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("qwen-plus-2025-04-28")
                .baseUrl("https://dashscope.aliyuncs.com/compatible-mode/v1")
                .build();
    }

    /**
     * {@link https://docs.langchain4j.dev/tutorials/tools#low-level-tool-api}
     * 低阶api调用案例
     */
    /*@Bean
    public FunctionCallAssistant functionCallAssistant() {
        // 工具说明
        ToolSpecification toolSpecification = ToolSpecification.builder()
                .name("开具发票助手")
                .description("根据用户提交的开票信息，开具发票")
                .parameters(JsonObjectSchema.builder()
                        .addStringProperty("companyName", "公司名称")
                        .addStringProperty("dutyNumber", "税号序列")
                        .addStringProperty("amount", "开票金额，保留两位有效数字")
                        .build())
                .build();

        // 业务逻辑
        ToolExecutor toolExecutor = (toolExecutionResult, memoryId)-> {
            System.out.println(toolExecutionResult.id());
            System.out.println(toolExecutionResult.name());
            String arguments = toolExecutionResult.arguments();
            System.out.println("arguments ===> " + arguments);
            return "开具成功";
        };

        return AiServices.builder(FunctionCallAssistant.class)
                .chatModel(chatModel())
                .tools(Map.of(toolSpecification, toolExecutor))
                .build();
    }*/

    /**
     * {@link https://docs.langchain4j.dev/tutorials/tools#high-level-tool-api}
     * 高阶api
     */
    @Bean
    public FunctionCallAssistant functionCallAssistant(InvoiceHandler handler) {
        return AiServices.builder(FunctionCallAssistant.class)
                .chatModel(chatModel())
                .tools(handler)
                .build();
    }
}
