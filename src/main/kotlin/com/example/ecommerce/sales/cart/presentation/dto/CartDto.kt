package com.example.ecommerce.sales.cart.presentation.dto

data class PostCartItemDto(
    val productId: Long,
    val quantity: Int,
    val unitPrice: Int
)