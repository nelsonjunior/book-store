package br.com.bookstore.currencyservice.proxy

import br.com.bookstore.currencyservice.domain.entities.CurrencyMultiRate
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestParam

@FeignClient(name = "fast-forex-service", url="localhost:9000")
interface CurrencyRateProxy {

    @GetMapping("fetch-multi")
    fun fetchMulti(
        @RequestParam("from") from: String,
        @RequestParam("to") to: List<String>
    ): CurrencyMultiRate
}