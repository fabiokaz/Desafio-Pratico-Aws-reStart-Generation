package com.fabiokaz.alunos_api.doc;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("CRUD API REST para o armazenamento de dados de alunos de uma escola")
                        .version("1.0")
                        .description("Desafio prático do curso de AWS re/Start da Generation.")
                        .description("http://github.com"));
    }
}