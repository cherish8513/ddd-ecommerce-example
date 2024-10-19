package com.example.ecommerce.sales.order.infrastructure.model

import com.example.ecommerce.common.infrastructure.ICode

enum class OrderStatus(
    override val description: String,
    override val code: Char
) : ICode {
    PREPARE_DELIVERY("배송준비", '1'),
    PAY_CANCEL("결제취소", '9'),
}