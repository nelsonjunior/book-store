package br.com.bookstore.book.controllers

import br.com.bookstore.book.services.BookService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/books")
class BookController(
    val service: BookService
) {

    @GetMapping("/{id}/{currency}")
    fun findBook(
        @PathVariable("id") id: Long,
        @PathVariable("currency") currency: String
    ) = service.findBook(id, currency)

}