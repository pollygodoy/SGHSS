package com.vidaplus.sghss.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("SGHSS - Sistema de Gestão Hospitalar e de Serviços de Saúde")
                        .version("1.0.0")
                        .description("API REST para gerenciamento de histórico de saúde dos pacientes")
                        .contact(new Contact()
                                .name("Poliane Godoy")
                                .email("pollygodoycurso@gmail.com")
                                .url("https://vidaplus.com.br")
                        )
                        .license(new License()
                                .name("Apache 2.0")
                                .url("https://www.apache.org/licenses/LICENSE-2.0.html")
                        )
                );
    }
}

