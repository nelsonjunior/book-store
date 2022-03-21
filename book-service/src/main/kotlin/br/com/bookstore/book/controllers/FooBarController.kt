package br.com.bookstore.book.controllers

import io.github.resilience4j.bulkhead.annotation.Bulkhead
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker
import io.github.resilience4j.ratelimiter.annotation.RateLimiter
import io.github.resilience4j.retry.annotation.Retry
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.RestTemplate

@Tag(name = "FooBar endpoint")
@RestController
@RequestMapping("/books")
class FooBarController(
    val restTemplate: RestTemplate
) {

    private val logger = LoggerFactory.getLogger(FooBarController::class.java)

    @Operation(summary = "Test endpoint with retry and bulkhead")
    @GetMapping("/foo-bar-retry")
    @Retry(name = "default", fallbackMethod = "defaultFooBar")
    @Bulkhead(name = "default")
    fun foobarRetry() : String {
        logger.info("foo-bar request")
        val response = restTemplate.getForEntity("http://localhost:8080/foo-bar", String.javaClass)
        return response.body.toString()
    }

    @Operation(summary = "Test endpoint with circuit breaker")
    @GetMapping("/foo-bar-circuit-break")
    @CircuitBreaker(name = "default", fallbackMethod = "defaultCircuitBreakFooBar")
    fun foobarCircuitBreaker() : String {
        logger.info("foo-bar request")
        val response = restTemplate.getForEntity("http://localhost:8080/foo-bar", String.javaClass)
        return response.body.toString()
    }

    @Operation(summary = "Test endpoint with circuit breaker and rate limiter")
    @GetMapping("/foo-bar-rate-limiter")
    @RateLimiter(name = "default")
    @CircuitBreaker(name = "default", fallbackMethod = "defaultCircuitBreakFooBar")
    fun foobarRateLimiter() : String {
        logger.info("foo-bar request")
        val response = restTemplate.getForEntity("http://localhost:8080/foo-bar", String.javaClass)
        return response.body.toString()
    }

    fun defaultFooBar(ex: Exception):String {
        return "Hello foo bar!!!"
    }

    fun defaultCircuitBreakFooBar(ex: Exception):String {
        return "Circuit Break Hello foo bar!!!"
    }
}