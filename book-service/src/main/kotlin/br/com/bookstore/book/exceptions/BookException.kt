package br.com.bookstore.book.exceptions

class BookException : RuntimeException {

    constructor(message: String) : super(message) {}
    constructor(message: String, throwable: Throwable) : super(message, throwable) {}
}