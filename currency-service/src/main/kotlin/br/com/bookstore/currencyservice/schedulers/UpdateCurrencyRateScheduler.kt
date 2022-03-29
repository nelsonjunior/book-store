package br.com.bookstore.currencyservice.schedulers

import br.com.bookstore.currencyservice.proxy.CurrencyRateProxy
import br.com.bookstore.currencyservice.services.UpdateCurrencyService
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Value
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service


@Service
class UpdateCurrencyRateScheduler (
    val service: UpdateCurrencyService,
    @Value("\${currency.from}")
    val from: String,
    @Value("\${currency.to}")
    val to: List<String>
) {
    private val logger = LoggerFactory.getLogger(UpdateCurrencyRateScheduler::class.java)

    @Scheduled(cron = "\${scheduler.cron}")
    protected fun execute() {

        logger.info("UpdateCurrencyRateScheduler $from : $to")

        service.updateCurrency(from, to)
    }
}