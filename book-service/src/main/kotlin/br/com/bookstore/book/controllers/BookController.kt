package br.com.bookstore.book.controllers

import br.com.bookstore.book.domain.dto.BookDTO
import br.com.bookstore.book.domain.dto.ExchangeDTO
import br.com.bookstore.book.exceptions.BookException
import br.com.bookstore.book.repositorieis.BookRepository
import br.com.bookstore.book.services.ExchangeService
import org.springframework.core.env.Environment
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/books")
class BookController(
    val environment: Environment,
    val exchangeService: ExchangeService,
    val repository: BookRepository
) {

    @GetMapping("/{id}/{currency}")
    fun findBook(
        @PathVariable("id") id: Long,
        @PathVariable("currency") currency: String
    ) : BookDTO {

        val environmentPort = "Port: ${environment.getProperty("local.server.port")}";

        val book = repository.findBookById(id)

        if (!book.isPresent) {
            throw BookException("Book not found")
        }

        val exchange = exchangeService.getExchange(book.get().price, "USD", currency)

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