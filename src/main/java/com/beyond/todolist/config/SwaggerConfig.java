package com.beyond.todolist.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Todo List API",
                version = "1.0",
                description = "API documentation for Todo List application"
        )
)
public class SwaggerConfig {

    @Bean
    public OpenAPI OpenAPI() {
       return new OpenAPI().components(new Components());
    }


}