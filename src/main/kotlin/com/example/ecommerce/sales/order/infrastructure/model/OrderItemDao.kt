package com.example.ecommerce.sales.order.infrastructure.model

import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@QueryEntity
@Table(name = "TbOrderItem")
class OrderItemDao(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val orderItemId: Long? = null,
    @Column
    val productId: Long,
    @Column
    val quantity: Int,
) {
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    var order: OrderDao? = null
}