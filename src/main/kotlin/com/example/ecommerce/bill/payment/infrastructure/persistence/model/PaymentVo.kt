package com.example.ecommerce.bill.payment.infrastructure.persistence.model

import com.example.ecommerce.common.infrastructure.ICode

enum class PaymentMethod(
    override val description: String,
    override val code: Char
) : ICode {
    CASH("현금", '1'),
    CARD("카드", '2')
}