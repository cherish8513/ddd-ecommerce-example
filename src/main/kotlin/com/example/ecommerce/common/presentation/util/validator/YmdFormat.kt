package com.example.ecommerce.common.presentation.util.validator

import jakarta.validation.Constraint
import jakarta.validation.Payload
import kotlin.annotation.AnnotationRetention.RUNTIME
import kotlin.annotation.AnnotationTarget.FIELD
import kotlin.reflect.KClass

@Constraint(validatedBy = [YmdFormatValidator::class])
@Target(
    FIELD,
    AnnotationTarget.PROPERTY_GETTER,
    AnnotationTarget.PROPERTY_SETTER
)
@Retention(RUNTIME)
@MustBeDocumented
annotation class YmdFormat(
    val pattern: String = "yyyyMMdd",
    val message: String = "유효하지 않은 날짜 형식입니다.",
    val groups: Array<KClass<*>> = [],
    val payload: Array<KClass<out Payload>> = []
)