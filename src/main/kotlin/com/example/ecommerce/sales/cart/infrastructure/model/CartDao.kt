package com.example.ecommerce.sales.cart.infrastructure.model

import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@QueryEntity
@Table(name = "TbCart")
class CartDao(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val cartId: Long? = null,
    val userId: Long,
) {
    @OneToMany(mappedBy = "cart", fetch = FetchType.LAZY)
    var cartItems: MutableList<CartItemDao> = mutableListOf()

    fun addCartItem(item: CartItemDao) {
        cartItems.add(item)
        item.cart = this
    }
}