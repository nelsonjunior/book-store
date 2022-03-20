package br.com.bookstore.exchange.repositories

import br.com.bookstore.exchange.domain.entities.Exchange
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExchangeRepository : JpaRepository<Exchange, Long> {

    fun findByFromAndTo(from: String, to: String): Optional<Exchange>

}