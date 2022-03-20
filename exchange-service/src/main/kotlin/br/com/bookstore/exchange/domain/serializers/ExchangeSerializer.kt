package br.com.bookstore.exchange.domain.serializers

import br.com.bookstore.exchange.domain.dto.ExchangeDTO
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.LoggerFactory
import org.springframework.data.redis.serializer.RedisSerializer

class ExchangeSerializer : RedisSerializer<ExchangeDTO> {

    private val mapper = jacksonObjectMapper()
    private val log = LoggerFactory.getLogger(javaClass)

    override fun serialize(t: ExchangeDTO?): ByteArray? {
        log.info("Serializing ExchangeDTO...")
        return mapper.writeValueAsBytes(t);
    }

    override fun deserialize(data: ByteArray?): ExchangeDTO? {
        log.info("Deserializing ExchangeDTO...")
        return mapper.readValue<ExchangeDTO>(data, ExchangeDTO::class.java)
    }
}