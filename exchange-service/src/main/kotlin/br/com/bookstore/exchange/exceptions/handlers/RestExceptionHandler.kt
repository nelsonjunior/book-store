package br.com.bookstore.exchange.exceptions.handlers

import br.com.bookstore.exchange.domain.dto.ExceptionResponseDTO
import br.com.bookstore.exchange.exceptions.ExchangeException
import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus
import org.springframework.web.context.request.WebRequest
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.util.stream.Collectors
import javax.persistence.EntityNotFoundException

@ControllerAdvice
class RestExceptionHandler : ResponseEntityExceptionHandler() {

    override fun handleMethodArgumentNotValid(
        ex: MethodArgumentNotValidException,
        headers: HttpHeaders,
        status: HttpStatus,
        request: WebRequest
    ): ResponseEntity<Any> {
        val errors: Set<String> = ex.bindingResult.fieldErrors.stream()
            .map { f -> "${f.field} : ${f.defaultMessage}" }
            .collect(Collectors.toSet())

        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.BAD_REQUEST.name,
                HttpStatus.BAD_REQUEST.reasonPhrase,
                errors), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(Exception::class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    fun handleException(e: Exception): ResponseEntity<Any?>? {
        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.INTERNAL_SERVER_ERROR.name,
                e.message), HttpStatus.INTERNAL_SERVER_ERROR
        )
    }

    @ExceptionHandler(EntityNotFoundException::class)
    fun handleEntityNotFoundException(ex: EntityNotFoundException): ResponseEntity<Any?>? {
        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.NOT_FOUND.name,
                HttpStatus.NOT_FOUND.reasonPhrase,
                setOf(ex.message)
            ), HttpStatus.NOT_FOUND
        )
    }

    @ExceptionHandler(IllegalArgumentException::class)
    fun handleEntityNotFoundException(ex: IllegalArgumentException): ResponseEntity<Any?>? {
        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.BAD_REQUEST.name,
                HttpStatus.BAD_REQUEST.reasonPhrase,
                setOf(ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }

    @ExceptionHandler(ExchangeException::class)
    fun handleEntityNotFoundException(ex: ExchangeException): ResponseEntity<Any?>? {
        return ResponseEntity(
            ExceptionResponseDTO(
                HttpStatus.BAD_REQUEST.name,
                HttpStatus.BAD_REQUEST.reasonPhrase,
                setOf(ex.message)
            ), HttpStatus.BAD_REQUEST
        )
    }

}