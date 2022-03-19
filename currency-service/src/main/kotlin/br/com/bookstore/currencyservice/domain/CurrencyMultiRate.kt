package br.com.bookstore.currencyservice.domain

import java.time.LocalDateTime

data class CurrencyMultiRate(
    val base: String,
    val results: Map<String, Double>,
    val updated: LocalDateTime
){
    override fun toString(): String {
        return "CurrencyMultiRate(base='$base', results=$results, updated=$updated)"
    }
}