package com.example.ecommerce.common.presentation.exception

import com.example.api.static.exception.CustomException
import com.example.api.static.exception.CustomExceptionType
import com.example.ecommerce.common.presentation.util.dto.Meta
import com.example.ecommerce.common.presentation.util.dto.ResponseDto
import jakarta.servlet.http.HttpServletRequest
import jakarta.validation.ConstraintViolationException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class GlobalControllerAdvice {
    @ExceptionHandler(value = [MethodArgumentNotValidException::class])
    fun methodArgumentNotValidException(
        exception: MethodArgumentNotValidException,
        request: HttpServletRequest
    ): ResponseEntity<ResponseDto<Unit>> {
        val messages = StringBuilder()

        exception.bindingResult.fieldErrors.forEach { fieldError ->
            if (messages.isNotEmpty()) {
                messages.append(", ")
            }
            messages.append("${fieldError.defaultMessage}")
        }

        return errorResponseEntityGenerator(HttpStatus.BAD_REQUEST, messages.toString())
    }

    @ExceptionHandler(value = [ConstraintViolationException::class])
    fun constraintViolationException(
        exception: ConstraintViolationException,
        request: HttpServletRequest
    ): ResponseEntity<ResponseDto<Unit>> {
        val messages = StringBuilder()

        exception.constraintViolations.forEach { fieldError ->
            if (messages.isNotEmpty()) {
                messages.append(", ")
            }
            messages.append(fieldError.message)
        }

        return errorResponseEntityGenerator(HttpStatus.BAD_REQUEST, messages.toString())
    }

    @ExceptionHandler(value = [CustomException::class])
    fun customExceptionHandler(
        customException: CustomException,
        request: HttpServletRequest
    ): ResponseEntity<ResponseDto<Unit>> {
        return errorResponseEntityGenerator(
            httpStatus = customException.exceptionType.httpStatus,
            message = customException.exceptionType.message
        )
    }

    @ExceptionHandler(value = [Exception::class])
    fun exceptionHandler(
        exception: Exception,
        request: HttpServletRequest
    ): ResponseEntity<ResponseDto<Unit>> {
        return errorResponseEntityGenerator(
            httpStatus = HttpStatus.INTERNAL_SERVER_ERROR,
            message = CustomExceptionType.UNEXPECTED_ERROR_OCCURRED.message
        )
    }

    private fun errorResponseEntityGenerator(
        httpStatus: HttpStatus,
        message: String,
    ): ResponseEntity<ResponseDto<Unit>> {
        return ResponseEntity.status(httpStatus).body(
            ResponseDto(
                meta = Meta(
                    code = httpStatus.value().toString(),
                    message = message,
                    data = null
                )
            )
        )
    }
}