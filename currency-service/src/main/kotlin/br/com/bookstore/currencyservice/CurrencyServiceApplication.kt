package br.com.bookstore.currencyservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableFeignClients
@EnableScheduling
class CurrencyServiceApplication

fun main(args: Array<String>) {
    runApplication<CurrencyServiceApplication>(*args)
}
