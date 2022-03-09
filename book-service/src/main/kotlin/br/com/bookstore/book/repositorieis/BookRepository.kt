package br.com.bookstore.book.repositorieis

import br.com.bookstore.book.domain.entities.Book
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.*

@Repository
interface BookRepository : JpaRepository<Book, Long> {

    fun findBookById(id: Long): Optional<Book>
}