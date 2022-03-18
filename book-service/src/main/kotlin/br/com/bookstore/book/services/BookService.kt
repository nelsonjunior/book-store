package br.com.bookstore.book.services

import br.com.bookstore.book.domain.dto.BookDTO
import br.com.bookstore.book.exceptions.BookException
import br.com.bookstore.book.proxy.ExchangeProxy
import br.com.bookstore.book.repositorieis.BookRepository
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service

@Service
class BookService(
    val environment: Environment,
    val exchangeProxy: ExchangeProxy,
    val repository: BookRepository
) {

    fun findBook(id: Long, currency: String
    ) : BookDTO {

        val book = repository.findBookById(id)

        if (!book.isPresent) {
            throw BookException("Book not found")
        }

        val exchange = exchangeProxy.getExchange(book.get().price, "USD", currency)

        val environmentPort = "Book port: ${environment.getProperty("local.server.port")} - Exchange port: ${exchange.environment}";

        return BookDTO(
            book.get().id,
            book.get().author,
            book.get().launchDate,
            exchange.convertedValue,
            book.get().title,
            currency,
            environmentPort
        )
    }
}