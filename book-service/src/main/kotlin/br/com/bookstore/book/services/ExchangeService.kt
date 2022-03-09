package br.com.bookstore.book.services

import br.com.bookstore.book.domain.dto.ExchangeDTO
import br.com.bookstore.book.exceptions.BookException
import org.springframework.stereotype.Component
import org.springframework.web.client.RestTemplate

@Component
class ExchangeService(
    val restTemplate: RestTemplate) {

    fun getExchange(value: Double, from: String, to: String): ExchangeDTO {

        val params: Map<String, *> = hashMapOf(
            "amount" to value,
            "from" to from,
            "to" to to
        )


        val response = restTemplate
            .getForEntity(
                "http://localhost:8000/exchange/{amount}/{from}/{to}",
                ExchangeDTO().javaClass,
                params
            )

        return response.body ?: throw BookException("Exchange price error")
    }
}