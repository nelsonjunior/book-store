package br.com.bookstore.book.domain.dto

data class ExceptionResponseDTO(
    val code: String,
    val message: String?,
    val details: Set<String?>? = null
)