package com.example.ecommerce.sales.cart.infrastructure.repository.impl

import com.example.ecommerce.common.presentation.util.assertNotNull
import com.example.ecommerce.sales.cart.Cart
import com.example.ecommerce.sales.cart.CartItem
import com.example.ecommerce.sales.cart.CartRepository
import com.example.ecommerce.sales.cart.infrastructure.repository.CartJpaRepository

class CartRepositoryImpl(
    private val cartJpaRepository: CartJpaRepository
): CartRepository {
    override fun findByUserIdAndCartId(userId: Long, cartId: Long): Cart {
        return cartJpaRepository.findByCartIdAndUserId(cartId, userId).assertNotNull().let { cartDao -> Cart(
            cartId = cartId,
            userId = userId,
            cartItems = cartDao.cartItems.map { CartItem(
                productId = it.productId,
                quantity = it.quantity,
                unitPrice = it.unitPrice
            ) }
        ) }
    }
}