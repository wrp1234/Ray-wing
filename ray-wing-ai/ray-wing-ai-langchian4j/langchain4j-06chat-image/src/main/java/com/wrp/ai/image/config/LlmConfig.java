package com.wrp.ai.image.config;

import dev.langchain4j.community.model.dashscope.WanxImageModel;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author wrp
 * @since 2025/7/13 8:55
 **/
@Configuration
public class LlmConfig {

    @Bean
    public WanxImageModel wanxImageModel() {
        return WanxImageModel.builder()
                .apiKey(System.getenv("ALI_AI_KEY"))
                .modelName("wanx2.1-t2i-turbo") // 图片生成
                .build();
    }
}
