package com.example.ecommerce.sales.cart

import com.example.ecommerce.sales.cart.presentation.dto.PostCartItemDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class CartService(
    private val cartRepository: CartRepository
) {
    fun addCartItem(userId: Long, cartId: Long, postCartItemDto: PostCartItemDto) {
        return cartRepository.addNewItem(NewCartItem(
            userId = userId,
            cartId = cartId,
            productId = postCartItemDto.productId,
            quantity = postCartItemDto.quantity,
            unitPrice = postCartItemDto.unitPrice
        ))
    }
    fun getCart(userId: Long): Cart {
        return cartRepository.findByUserId(userId)
    }
}