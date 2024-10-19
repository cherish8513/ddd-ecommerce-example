package com.example.ecommerce.sales.cart.presentation

import com.example.ecommerce.common.presentation.ApiController
import com.example.ecommerce.common.presentation.util.dto.ResponseDto
import com.example.ecommerce.sales.cart.CartService
import com.example.ecommerce.sales.cart.presentation.dto.PostCartItemDto
import com.example.ecommerce.user.presentation.dto.UserSessionDto
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@ApiController
@RequestMapping("/carts")
class CartController(
    private val cartService: CartService,
) {
    @PostMapping
    fun addCart(@Valid @RequestBody postCartItemDto: PostCartItemDto, @AuthenticationPrincipal userSessionDto: UserSessionDto): ResponseDto<Unit> {
        cartService.addCartItem(userId = userSessionDto.userId, cartId = userSessionDto.cartId, postCartItemDto = postCartItemDto)
        return ResponseDto(orderService.addOrderFromCart(cart))
    }
}