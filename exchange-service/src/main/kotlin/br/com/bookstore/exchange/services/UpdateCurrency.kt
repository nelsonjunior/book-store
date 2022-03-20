package br.com.bookstore.exchange.services

import br.com.bookstore.exchange.domain.entities.CurrencyMultiRate
import br.com.bookstore.exchange.repositories.ExchangeRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service

@Service
class UpdateCurrency (
    val repository: ExchangeRepository
){

    @CacheEvict("exchange")
    fun updateCurrency(currencyMultiRate: CurrencyMultiRate) {

        val from = currencyMultiRate.base

        currencyMultiRate.results
            .forEach{
                val exchange = repository.findByFromAndTo(from, it.key)
                if(exchange.isPresent) {
                    var updateExchange = exchange.get()
                    updateExchange.conversionFactor = it.value.toBigDecimal()
                    repository.save(updateExchange)
                }
            }
    }
}