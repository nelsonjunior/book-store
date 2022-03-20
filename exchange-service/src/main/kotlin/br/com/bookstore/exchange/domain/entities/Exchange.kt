package br.com.bookstore.exchange.domain.entities

import org.hibernate.annotations.DynamicUpdate
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "exchange")
@DynamicUpdate
class Exchange(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long,

    @Column(name = "from_currency", nullable = false, length = 3)
    var from: String,

    @Column(name = "to_currency", nullable = false, length = 3)
    var to: String,

    @Column(nullable = false)
    var conversionFactor: BigDecimal
)