package com.example.ecommerce.sales.cart

data class CartItem (
    val productId: Long,
    val quantity: Int,
    val unitPrice: Int,
)

data class NewCartItem(
    val userId: Long,
    val cartId: Long,
    val productId: Long,
    val quantity: Int,
    val unitPrice: Int
)