package br.com.bookstore.exchange.domain.deserializers

import br.com.bookstore.exchange.domain.entities.CurrencyMultiRate
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.apache.commons.lang.SerializationException
import org.slf4j.LoggerFactory
import org.apache.kafka.common.serialization.Deserializer

class CurrencyMultRateDeserializer : Deserializer<CurrencyMultiRate> {

    private val mapper = jacksonObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun deserialize(topic: String?, data: ByteArray?): CurrencyMultiRate? {
        log.info("Deserializing...")

        return mapper.readValue<CurrencyMultiRate>(String(
            data ?: throw SerializationException("Error when deserializing byte[] to CurrencyMultiRate"), Charsets.UTF_8
            ), CurrencyMultiRate::class.java)
    }

    override fun close() {}
}