package br.com.bookstore.currencyservice.domain.serializer

import br.com.bookstore.currencyservice.domain.entities.CurrencyMultiRate
import br.com.bookstore.currencyservice.domain.entities.CurrencyRate
import com.fasterxml.jackson.databind.ObjectMapper
import org.apache.commons.lang.SerializationException
import org.apache.kafka.common.serialization.Serializer
import org.slf4j.LoggerFactory

class CurrencySerializer : Serializer<CurrencyMultiRate> {

    private val objectMapper = ObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(topic: String?, data: CurrencyMultiRate?): ByteArray? {
        log.info("Serializing...")
        return objectMapper.writeValueAsBytes(
            data ?: throw SerializationException("Error when serializing Product to ByteArray[]")
        )
    }

    override fun close() {}
}