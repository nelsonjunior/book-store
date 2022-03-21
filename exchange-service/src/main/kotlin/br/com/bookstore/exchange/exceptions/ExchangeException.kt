package br.com.bookstore.exchange.exceptions

class ExchangeException : RuntimeException {

    constructor(message: String) : super(message)
    constructor(message: String, throwable: Throwable) : super(message, throwable)
}