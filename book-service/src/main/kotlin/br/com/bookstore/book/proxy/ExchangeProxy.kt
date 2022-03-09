package br.com.bookstore.book.proxy

import br.com.bookstore.book.domain.dto.ExchangeDTO
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable

@FeignClient(name ="exchange-service", url = "localhost:8000")
interface ExchangeProxy {

    @GetMapping("exchange/{amount}/{from}/{to}")
    fun getExchange(
        @PathVariable("amount") amount: Double,
        @PathVariable("from") from: String,
        @PathVariable("to") to: String
    ): ExchangeDTO
}