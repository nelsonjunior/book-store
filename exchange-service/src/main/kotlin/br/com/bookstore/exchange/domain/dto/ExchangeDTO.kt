package br.com.bookstore.exchange.domain.dto

import java.math.BigDecimal

data class ExchangeDTO(
    val id: Long,
    val from: String,
    val to: String,
    val conversionFactor: BigDecimal,
    val convertedValue: BigDecimal,
    val environment: String
)
