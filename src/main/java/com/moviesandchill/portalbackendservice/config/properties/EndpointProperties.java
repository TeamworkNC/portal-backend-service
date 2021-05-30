package com.moviesandchill.portalbackendservice.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "endpoints")
@Data
public class EndpointProperties {
    private String userServiceUrl;
    private String filmServiceUrl;
    private String recommendationServiceUrl;
    private String streamServiceUrl;
    private String chatServiceUrl;
}
