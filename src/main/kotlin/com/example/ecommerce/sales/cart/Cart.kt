package com.example.ecommerce.sales.cart

data class Cart(
    val cartId: Long,
    val userId: Long,
    val cartItems: List<CartItem>,
)