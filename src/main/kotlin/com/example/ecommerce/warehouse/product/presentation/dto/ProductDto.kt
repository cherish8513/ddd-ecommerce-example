package com.example.ecommerce.warehouse.product.presentation.dto

import com.example.ecommerce.common.presentation.util.validator.YmdFormat
import com.example.ecommerce.seller.product.infrastructure.persistence.model.Size

data class SaveProductDto(
    val productId: Long? = null,
    val name: String,
    val price: Long,
    val description: String,
    val barcode: String,
    @field:YmdFormat(message = "날짜 패턴이 올바르지 않습니다. ex)20001225")
    val expirationYmd: String,
    val size: com.example.ecommerce.seller.product.infrastructure.persistence.model.Size
)

data class GetProductPageDto(
    val productId: Long? = null,
    val limit: Long = 10,
    val nameCondition: String? = null,
)

data class ProductDto(
    val productId: Long,
    val name: String,
    val price: Long,
    val description: String,
    val barcode: String,
    val expirationYmd: String,
    val size: com.example.ecommerce.seller.product.infrastructure.persistence.model.Size
)