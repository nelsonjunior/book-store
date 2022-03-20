package br.com.bookstore.currencyservice.services

import br.com.bookstore.currencyservice.domain.entities.CurrencyRate
import br.com.bookstore.currencyservice.producers.UpdateCurrencyProducer
import br.com.bookstore.currencyservice.proxy.CurrencyRateProxy
import br.com.bookstore.currencyservice.schedulers.UpdateCurrencyRateScheduler
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class UpdateCurrencyService (
    val proxy: CurrencyRateProxy,
    val producer: UpdateCurrencyProducer
        ){

    private val logger = LoggerFactory.getLogger(UpdateCurrencyService::class.java)

    fun updateCurrency(from: String, to: List<String>) {

        val currencyMultiRate = proxy.fetchMulti(from, to)

        logger.info("updateCurrency $currencyMultiRate")

        producer.producer(currencyMultiRate)

    }
}