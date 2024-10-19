package com.example.ecommerce.common.presentation.util.dto

class PageDto<T>(
    val data: T,
    val totalCount: Long,
    val isLast: Boolean?
)