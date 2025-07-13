package com.wrp.ai.prompt.entity;

import dev.langchain4j.model.input.structured.StructuredPrompt;
import lombok.Data;

/**
 * @author wrp
 * @since 2025/7/13 15:14
 **/
@Data
@StructuredPrompt("根据中国{{legal}}法律，解答以下问题：{{question}}，字数控制在{{length}}个字以内")
public class LawPrompt {

    private String legal;
    private String question;
    private int length;
}
