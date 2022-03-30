package br.com.bookstore.book.config

import com.fasterxml.jackson.core.JsonProcessingException
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.actuate.trace.http.HttpTrace
import org.springframework.boot.actuate.trace.http.HttpTraceRepository
import org.springframework.boot.actuate.trace.http.InMemoryHttpTraceRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class SpringActuatorConfig {

    @Bean
    fun httpTraceRepository(): HttpTraceRepository? {
        return object : InMemoryHttpTraceRepository() {
            var objectMapper = ObjectMapper().registerModule(JavaTimeModule())
            var logger: Logger = LoggerFactory.getLogger(InMemoryHttpTraceRepository::class.java)
            override fun add(trace: HttpTrace?) {
                try {
                    logger.info(objectMapper.writeValueAsString(trace))
                } catch (e: JsonProcessingException) {
                    logger.error(e.message, e)
                }
                super.add(trace)
            }
        }
    }
}