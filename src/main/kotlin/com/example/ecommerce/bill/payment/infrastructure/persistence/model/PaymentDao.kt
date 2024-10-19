package com.example.ecommerce.bill.payment.infrastructure.persistence.model

import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@QueryEntity
@Table(name = "TbPayment")
class PaymentDao(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val paymentId: Long? = null,
    @Column
    val userId: Long,
    @Column
    @Enumerated(STRING)
    val paymentMethod: PaymentMethod,
)