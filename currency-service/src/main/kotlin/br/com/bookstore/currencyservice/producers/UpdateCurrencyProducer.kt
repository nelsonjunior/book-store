package br.com.bookstore.currencyservice.producers

import br.com.bookstore.currencyservice.domain.entities.CurrencyMultiRate
import br.com.bookstore.currencyservice.domain.entities.CurrencyRate
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.support.KafkaHeaders
import org.springframework.messaging.support.MessageBuilder
import org.springframework.stereotype.Component

@Component
class UpdateCurrencyProducer(
    @Value("\${kafka.topics.currency-update}")
    val topic: String,
    val kafkaTemplate: KafkaTemplate<String, Any>
) {

    fun producer(currencyMultiRate: CurrencyMultiRate) {
        val message = MessageBuilder
            .withPayload(currencyMultiRate)
            .setHeader(KafkaHeaders.TOPIC, topic)
            .build()

        kafkaTemplate.send(message)
    }
}