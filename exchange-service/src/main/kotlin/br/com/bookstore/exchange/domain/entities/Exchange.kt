package br.com.bookstore.exchange.domain.entities

import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "exchange")
data class Exchange(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "from_currency", nullable = false, length = 3)
    val from: String,

    @Column(name = "to_currency", nullable = false, length = 3)
    val to: String,

    @Column(nullable = false)
    val conversionFactor: BigDecimal
)