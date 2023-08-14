package com.soni.consumer.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppConfig {
    @Bean
    public OpenAPI customOpenAPI() {

        return new OpenAPI().info(
                new Info().version("v1").title("Account management API")
                        .description("(NOTE: WSDL adapter implementation.)")
                        .contact(new Contact().name("SB")));
    }
}
