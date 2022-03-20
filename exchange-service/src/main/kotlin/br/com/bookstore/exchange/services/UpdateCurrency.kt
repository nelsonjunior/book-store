package br.com.bookstore.exchange.services

import br.com.bookstore.exchange.domain.entities.CurrencyMultiRate
import br.com.bookstore.exchange.repositories.ExchangeRepository
import org.springframework.cache.annotation.CacheEvict
import org.springframework.stereotype.Service

@Service
class UpdateCurrency (
    val exchangeService: ExchangeService
){

    fun updateCurrency(currencyMultiRate: CurrencyMultiRate) {

        val from = currencyMultiRate.base

        currencyMultiRate.results
            .forEach{
                exchangeService.updateConversionFactor(from, it.key, it.value.toBigDecimal())
            }
    }
}