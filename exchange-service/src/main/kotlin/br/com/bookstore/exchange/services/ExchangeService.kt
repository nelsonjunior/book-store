package br.com.bookstore.exchange.services

import br.com.bookstore.exchange.domain.dto.ExchangeDTO
import br.com.bookstore.exchange.domain.entities.Exchange
import br.com.bookstore.exchange.exceptions.ExchangeException
import br.com.bookstore.exchange.repositories.ExchangeRepository
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import java.math.BigDecimal
import java.math.RoundingMode

@Service
class ExchangeService(
    val environment: Environment,
    val repository: ExchangeRepository
) {

    fun getExchange(amount: BigDecimal, from: String, to: String) : ExchangeDTO {

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