package com.renan.pbd.ms_authentication.configuration;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI swaggerOpenAPI(){
        return new OpenAPI()
                .info(new Info()
                        .title("Authentication")
                        .version("1.0.0v")
                        .description("Microservice for creation and validation of token JWT"))
                .externalDocs(new ExternalDocumentation()
                        .description("Link to the project repository.")
                        .url("https://github.com/PBD-2024-1-Imobilt/ms_authentication"));
    }
}
