package br.com.bookstore.book.config

import org.springframework.boot.web.client.RestTemplateBuilder
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.client.RestTemplate
import java.time.Duration

@Configuration
class RestTemplateConfig {

    @Bean
    fun restTemplate():RestTemplate {
        return RestTemplateBuilder()
            .setConnectTimeout(Duration.ofSeconds(1))
            .build()
    }
}