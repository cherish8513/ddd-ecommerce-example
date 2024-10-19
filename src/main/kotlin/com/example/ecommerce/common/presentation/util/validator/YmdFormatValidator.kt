package com.example.ecommerce.common.presentation.util.validator

import com.example.api.static.exception.CustomExceptionType
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeParseException

class YmdFormatValidator : ConstraintValidator<YmdFormat, String> {
    private lateinit var annotation: YmdFormat

    override fun initialize(constraintAnnotation: YmdFormat) {
        this.annotation = constraintAnnotation
    }

    override fun isValid(dateFormatString: String, context: ConstraintValidatorContext): Boolean {
        val pattern = annotation.pattern
        return try {
            LocalDate.parse(dateFormatString, DateTimeFormatter.ofPattern(pattern))
            true
        } catch (e: DateTimeParseException) {
            throw CustomExceptionType.INVALID_PARAMETER.toException(annotation.message)
        }
    }
}