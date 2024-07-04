package com.bootcamp.consume_rest.config;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestConfig {
    @Bean
    RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder.build();
    }

    @Bean("restTemplateSmsGateway")
    RestTemplate restTemplateSmsGatewayConfig(RestTemplateBuilder builder) {
        return builder.build();
    }
}
