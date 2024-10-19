package com.example.ecommerce.common.presentation.util

import com.example.api.static.exception.CustomExceptionType
import com.example.api.static.exception.CustomExceptionType.UNEXPECTED_ERROR_OCCURRED
import com.example.ecommerce.common.presentation.util.dto.PageDto

fun <T> T?.assertNotNull(customExceptionType: CustomExceptionType? = null): T {
    return this ?: throw customExceptionType?.toException() ?: UNEXPECTED_ERROR_OCCURRED.toException("expected not null but null")
}

fun <T> List<T>.toPageDto(isLast: Boolean? = null): PageDto<List<T>> {
    return PageDto(
        data = this,
        totalCount = this.size.toLong(),
        isLast = isLast
    )
}