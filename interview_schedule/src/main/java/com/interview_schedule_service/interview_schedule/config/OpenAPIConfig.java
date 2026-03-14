package com.interview_schedule_service.interview_schedule.config;

import org.springframework.context.annotation.Bean;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenAPIConfig {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("Interview Service API")
                        .version("1.0")
                        .description("API documentation for Interview microservice"));
    }
}
//http://localhost:8081/swagger-ui/index.html