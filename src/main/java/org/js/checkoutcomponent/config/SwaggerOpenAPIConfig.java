package org.js.checkoutcomponent.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.info.BuildProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@OpenAPIDefinition(servers = @Server(url = "/", description = "Server URL"))
@Configuration
@RequiredArgsConstructor
public class SwaggerOpenAPIConfig {

    @Bean
    public BuildProperties createProperties() {
        return new org.springframework.boot.info.BuildProperties(new Properties());
    }

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(apiInfo());
    }

    private Info apiInfo() {
        return new Info().title("Checkout Component 3.0")
                .description("Checkout component.")
                .version(createProperties().getVersion());
    }
}
