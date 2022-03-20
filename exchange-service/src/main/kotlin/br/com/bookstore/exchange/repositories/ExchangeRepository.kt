package br.com.bookstore.exchange.repositories

import br.com.bookstore.exchange.domain.entities.Exchange
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface ExchangeRepository : JpaRepository<Exchange, Long> {

    @Cacheable("exchange")
    fun findByFromAndTo(from: String, to: String): Optional<Exchange>

}