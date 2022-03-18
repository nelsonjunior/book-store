package br.com.bookstore.book.config

import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import io.swagger.v3.oas.models.Components
import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.License
import org.springframework.context.annotation.Bean

@OpenAPIDefinition(info =
    Info(title = "Book Service API",
        version = "v1",
        description = "Documentation of Book Service API")
)
class OpenApiConfig {

    @Bean
    fun customOpenApi(): OpenAPI =
        OpenAPI().components(Components())
            .info(io.swagger.v3.oas.models.info.Info()
                .title("Book Service API")
                .version("v1")
                .license(License()
                    .name("Apache 2.0")
                    .url("https://www.apache.org/licenses/LICENSE-2.0")))
}