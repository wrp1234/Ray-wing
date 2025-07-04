package com.wrp.core.config;

import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import com.wrp.common.constant.DateConstant;
import org.springframework.boot.autoconfigure.jackson.Jackson2ObjectMapperBuilderCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * @author wrp
 * @since 2024年07月24日 14:52
 */
@Configuration
public class DateConfig {
    @Bean
    @Primary
    public Jackson2ObjectMapperBuilderCustomizer jackson2ObjectMapperBuilderCustomizer(){
                //日期转字符串
        return builder -> builder.serializerByType(LocalDate.class,
                        new LocalDateSerializer(DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_VALUE)))
                .serializerByType(LocalDateTime.class,
                        new LocalDateSerializer(DateTimeFormatter.ofPattern(DateConstant.DATE_TIME_FORMAT_VALUE)))
                .serializerByType(LocalTime.class,new LocalTimeSerializer(DateTimeFormatter.ofPattern(DateConstant.TIME_FORMAT_VALUE)))
                //字符串转日期
                .deserializerByType(LocalDateTime.class,new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern(DateConstant.DATE_TIME_FORMAT_VALUE)))
                .deserializerByType(LocalDate.class,new LocalDateDeserializer(DateTimeFormatter.ofPattern(DateConstant.DATE_FORMAT_VALUE)))
                .deserializerByType(LocalTime.class,new LocalTimeDeserializer(DateTimeFormatter.ofPattern(DateConstant.TIME_FORMAT_VALUE)));
    }
}
