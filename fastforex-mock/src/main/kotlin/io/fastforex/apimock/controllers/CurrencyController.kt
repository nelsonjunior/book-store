package io.fastforex.apimock.controllers

import io.fastforex.apimock.domain.CurrencyMultiRate
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.bind.annotation.RestController
import reactor.core.publisher.Mono
import java.math.BigDecimal
import java.time.LocalDateTime
import java.util.stream.Collectors
import kotlin.math.roundToInt
import kotlin.random.Random

@RestController
@RequestMapping("/")
class CurrencyController {

    val currencyRate = mapOf<String, Double>(
        "EUR" to 0.90179,
        "GBP" to 0.75982,
        "COP" to 3822.94658,
        "MXN" to 20.50862,
        "ARS" to 109.51068,
        "BRL" to 5.03644,
        "CLP" to 799.42254
    )

    @RequestMapping("/fetch-multi")
    @ResponseStatus(value = HttpStatus.OK)
    fun fetchMulti(
        @RequestParam(value = "from") from: String,
        @RequestParam(value = "to") to: MutableList<String>
    ): Mono<CurrencyMultiRate> = Mono.just(CurrencyMultiRate(
        from,
        currencyRate.filter { to.contains(it.key) }
            .map { it.key to randomValue(it.value).round() }
            .toMap(),
        LocalDateTime.now()
    ))

    fun randomValue(value: Double) = Random.nextDouble(value - 0.10, value + 0.10)

}

private fun Double.round(): Double = (this * 100000.0).roundToInt() / 100000.0
