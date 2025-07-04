package com.wrp.core.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author wrp
 * @since 2025年07月04日 21:34
 */
@Data
@Component
@ConfigurationProperties(prefix = "system")
public class SystemProperties {

    /**
     * 密钥
     */
    private String secret;
}
