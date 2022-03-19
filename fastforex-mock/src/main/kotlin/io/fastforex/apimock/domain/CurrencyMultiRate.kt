package io.fastforex.apimock.domain

import java.time.LocalDateTime

data class CurrencyMultiRate(
    val base: String,
    val results: Map<String, Double>,
    val updated: LocalDateTime
)