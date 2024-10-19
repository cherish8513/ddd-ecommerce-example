package com.example.ecommerce.sales.order


import com.example.ecommerce.sales.cart.Cart
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class OrderService(
    private val orderRepository: OrderRepository
) {
    fun addOrderFromCart(cart: Cart) {
        orderRepository.save(
            NewOrder(
                userId = cart.userId,
                orderItems = cart.cartItems.map { OrderItem(productId = it.productId, quantity = it.quantity) }
            )
        )
    }
}