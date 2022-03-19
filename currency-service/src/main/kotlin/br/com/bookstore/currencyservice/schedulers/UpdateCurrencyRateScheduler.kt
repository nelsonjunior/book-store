package br.com.bookstore.currencyservice.schedulers

import br.com.bookstore.currencyservice.proxy.CurrencyRateProxy
import org.slf4j.LoggerFactory
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Service


@Service
class UpdateCurrencyRateScheduler (
    val proxy: CurrencyRateProxy
) {
    private val logger = LoggerFactory.getLogger(UpdateCurrencyRateScheduler::class.java)

    @Scheduled(cron = "\${scheduler.cron}")
    private fun execute() {

        val multiRate = proxy.fetchMulti("USD", listOf("BRL", "EUR"))

        logger.info("Update Currency Rate Scheduler ${multiRate}")
    }
}