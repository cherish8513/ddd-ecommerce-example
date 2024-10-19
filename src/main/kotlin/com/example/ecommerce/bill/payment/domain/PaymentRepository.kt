package com.example.ecommerce.bill.payment.domain

interface PaymentRepository {
    fun save(newPayment: NewPayment): Long
}