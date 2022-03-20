package br.com.bookstore.currencyservice.domain.entities

import java.util.Date

data class CurrencyMultiRate(
    val base: String,
    val results: Map<String, Double>,
    val updated: Date
){
    override fun toString(): String {
        return "CurrencyMultiRate(base='$base', results=$results, updated=$updated)"
    }
}