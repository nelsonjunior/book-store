package br.com.bookstore.book.domain.entities

import java.util.*
import javax.persistence.*

@Entity(name="book")
data class Book(

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @Column(name = "author", nullable = false, length = 180)
    val author: String,

    @Column(name = "launch_date", nullable = false)
    @Temporal(TemporalType.DATE)
    val launchDate: Date,

    @Column(nullable = false)
    val price: Double,

    @Column(nullable = false, length = 250)
    val title: String,

)
