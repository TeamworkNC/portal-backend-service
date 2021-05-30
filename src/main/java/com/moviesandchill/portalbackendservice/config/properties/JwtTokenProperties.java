package com.moviesandchill.portalbackendservice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "jwt.token")
@Data
public class JwtTokenProperties {
    private String cookieName;
    private String secret;
    private long expired;
}
