package com.example.ecommerce.sales.cart

import org.springframework.stereotype.Repository

@Repository
interface CartRepository {
    fun findByUserId(userId: Long): Cart
    fun addNewItem(newCartItem: NewCartItem)
}