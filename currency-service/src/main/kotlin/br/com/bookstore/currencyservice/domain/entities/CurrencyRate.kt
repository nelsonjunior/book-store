package br.com.bookstore.currencyservice.domain.entities

import com.fasterxml.jackson.annotation.JsonProperty

data class CurrencyRate(
    @JsonProperty("currency")
    val currency: String,

    @JsonProperty("rate")
    val rate: Double
)