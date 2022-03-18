package br.com.bookstore.book.domain.dto

data class ExchangeDTO(
    val id: Long,
    val from: String,
    val to: String,
    val conversionFactor: Double,
    val convertedValue: Double,
    val environment: String
){
    constructor() : this(-1L, "", "", Double.NaN, Double.NaN, "")
}
