package br.com.bookstore.apigateway.config

import br.com.bookstore.apigateway.filter.LoggingFilter
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info
import org.slf4j.LoggerFactory
import org.springdoc.core.GroupedOpenApi
import org.springdoc.core.SwaggerUiConfigParameters
import org.springframework.cloud.gateway.route.RouteDefinitionLocator
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Lazy

@OpenAPIDefinition(info =
    Info(title = "Book Service API",
        version = "v1",
        description = "Documentation of Book Service API")
)
@Configuration
class OpenApiConfig {

    private val logger = LoggerFactory.getLogger(OpenApiConfig::class.java)

    @Bean
    @Lazy(false)
    fun apis(config: SwaggerUiConfigParameters,
        locator: RouteDefinitionLocator
    ) : List<GroupedOpenApi> {


//        locator.routeDefinitions.filter {
//            it.id.contains(".*-service")
//        }.map {
//            config.addGroup(it.id)
//            GroupedOpenApi.builder()
//                .pathsToMatch("/${it.id}/**")
//                .group(it.id)
//                .build()
//        }

        val definitions = locator.routeDefinitions.collectList().block()

        definitions!!.stream().filter {
            it.id.contains("-service")
        }.forEach {
            logger.info("route definition ${it.id}")
            config.addGroup(it.id)
            GroupedOpenApi.builder()
                .pathsToMatch("/${it.id}/**")
                .group(it.id)
                .build()
        }
        return emptyList()
    }
}