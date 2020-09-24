package com.kotlin.tour.advice

import com.fasterxml.jackson.core.JsonParseException
import com.kotlin.tour.exception.PromocaoNotFoundException
import com.kotlin.tour.model.ErrorMessage
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@ControllerAdvice
class ErrorHandler {
    @ExceptionHandler(JsonParseException::class)
    fun jsonExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("Json Error", exception.message ?: "Invalid Json"), HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(PromocaoNotFoundException::class)
    fun promocaoNotFoundExceptionHandler(servletRequest: HttpServletRequest, servletResponse: HttpServletResponse, exception: Exception): ResponseEntity<ErrorMessage> {
        return ResponseEntity(ErrorMessage("Promocao não localizada", exception.message!!), HttpStatus.NOT_FOUND)
    }
}

// ?: --> (a != null) ? a : null
// !! --> (a != null) ? a : algum objeto que consiga tratar