package com.example.ecommerce.sales.cart.infrastructure.model

import jakarta.persistence.Entity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "TbCartItem")
class CartItemDao(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val orderItemId: Long? = null,
    val productId: Long,
    val quantity: Int,
    val unitPrice: Int,
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "cart_id")
    var cart: CartDao? = null
}