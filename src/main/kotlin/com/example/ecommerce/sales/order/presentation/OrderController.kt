package com.example.ecommerce.sales.order.presentation

import com.example.ecommerce.common.presentation.ApiController
import com.example.ecommerce.common.presentation.util.dto.ResponseDto
import com.example.ecommerce.sales.cart.CartService
import com.example.ecommerce.sales.order.OrderService
import com.example.ecommerce.user.presentation.dto.UserSessionDto
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping

@ApiController
@RequestMapping("/orders")
class OrderController(
    private val orderService: OrderService,
    private val cartService: CartService,
) {
    @PostMapping
    fun addOrderFromCart(@AuthenticationPrincipal userSessionDto: UserSessionDto): ResponseDto<Unit> {
        val cart = cartService.getCart(userId = userSessionDto.userId)
        return ResponseDto(orderService.addOrderFromCart(cart))
    }
}