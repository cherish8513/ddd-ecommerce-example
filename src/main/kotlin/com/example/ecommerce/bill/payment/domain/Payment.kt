package com.example.ecommerce.bill.payment.domain

import com.example.ecommerce.bill.payment.infrastructure.persistence.model.PaymentDao
import com.example.ecommerce.bill.payment.infrastructure.persistence.model.PaymentMethod

data class Payment(
    val paymentId: Long,
    val userId: Long,
    val paymentMethod: PaymentMethod,
)

data class NewPayment(
    val userId: Long,
    val paymentMethod: PaymentMethod,
) {
    fun toEntity(): PaymentDao {
        return PaymentDao(
            userId = userId,
            paymentMethod = paymentMethod,
        )
    }
}