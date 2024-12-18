package com.javaacademy.admin.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customOpenApi() {
        Info info = new Info()
                .title("API продуктового магазина")
                .contact(new Contact().name("Alexandwere").url("https://t.me/Alexandwere"));
        return new OpenAPI().info(info);
    }

}
