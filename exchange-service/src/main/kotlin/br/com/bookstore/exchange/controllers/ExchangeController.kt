package br.com.bookstore.exchange.controllers

import br.com.bookstore.exchange.domain.dto.ExchangeDTO
import br.com.bookstore.exchange.domain.entities.Exchange
import br.com.bookstore.exchange.exceptions.ExchangeException
import br.com.bookstore.exchange.repositories.ExchangeRepository
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.math.BigDecimal
import java.math.RoundingMode

@Tag(name = "Exchange endpoint")
@RestController
@RequestMapping("exchange")
class ExchangeController(
    val environment: Environment,
    val repository: ExchangeRepository) {

    @Operation(summary = "Get exchange amount value curreny")
    @GetMapping("/{amount}/{from}/{to}")
    fun getExchange(
        @PathVariable("amount") amount: BigDecimal,
        @PathVariable("from") from: String,
        @PathVariable("to") to: String
    ): ExchangeDTO {

        val environmentPort = "Port: ${environment.getProperty("local.server.port")}";

        val exchange = repository.findByFromAndTo(from, to);

        if(!exchange.isPresent){
            throw ExchangeException("Currency Unsupported")
        }

        val convertedValue = exchange.get().conversionFactor.multiply(amount).setScale(2, RoundingMode.CEILING)

        return ExchangeDTO(
            exchange.get().id,
            exchange.get().from,
            exchange.get().to,
            exchange.get().conversionFactor,
            convertedValue,
            environmentPort
        )
    }


}