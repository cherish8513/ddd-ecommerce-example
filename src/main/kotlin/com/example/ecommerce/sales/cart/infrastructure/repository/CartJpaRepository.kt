package com.example.ecommerce.sales.cart.infrastructure.repository

import com.example.ecommerce.sales.cart.infrastructure.model.CartDao
import org.springframework.data.jpa.repository.JpaRepository

interface CartJpaRepository : JpaRepository<CartDao, Long> {
    fun findByCartIdAndUserId(cartId: Long, userId: Long): CartDao?
}