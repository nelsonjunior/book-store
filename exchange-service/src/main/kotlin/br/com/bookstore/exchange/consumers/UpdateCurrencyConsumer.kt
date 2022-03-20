package br.com.bookstore.exchange.consumers

import br.com.bookstore.exchange.domain.entities.CurrencyMultiRate
import br.com.bookstore.exchange.services.UpdateCurrency
import org.apache.kafka.clients.consumer.ConsumerRecord
import org.slf4j.LoggerFactory
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.kafka.support.Acknowledgment
import org.springframework.stereotype.Component

@Component
class UpdateCurrencyConsumer(
    val service: UpdateCurrency
){

    private val logger = LoggerFactory.getLogger(javaClass)

    @KafkaListener(topics = ["\${kafka.topics.currency-update}"], groupId = "ppr")
    fun listenGroupFoo(consumerRecord: ConsumerRecord<Any, CurrencyMultiRate>, ack: Acknowledgment) {
        logger.info("Message received {}", consumerRecord)
        service.updateCurrency(consumerRecord.value())
        ack.acknowledge()
    }
}