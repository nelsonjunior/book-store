package br.com.bookstore.book.domain.dto

import java.util.*

data class BookDTO(
    val id: Long,
   val author: String,
   val launchDate: Date,
   val price: Double,
   val title: String,
   val currency: String,
   val environment: String
)
