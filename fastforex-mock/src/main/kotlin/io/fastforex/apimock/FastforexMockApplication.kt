package io.fastforex.apimock

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FastforexMockApplication

fun main(args: Array<String>) {
    runApplication<FastforexMockApplication>(*args)
}
