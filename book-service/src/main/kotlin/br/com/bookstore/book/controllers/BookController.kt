package br.com.bookstore.book.controllers

import br.com.bookstore.book.services.BookService
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "Book endpoint")
@RestController
@RequestMapping("/books")
class BookController(
    val service: BookService
) {

    @Operation(summary = "Find book with value by ID and Currency")
    @GetMapping("/{id}/{currency}")
    fun findBook(
        @PathVariable("id") id: Long,
        @PathVariable("currency") currency: String
    ) = service.findBook(id, currency)

}