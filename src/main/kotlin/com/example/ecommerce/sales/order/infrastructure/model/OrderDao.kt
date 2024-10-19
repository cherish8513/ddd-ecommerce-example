package com.example.ecommerce.sales.order.infrastructure.model

import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY


@Entity
@QueryEntity
@Table(name = "TbOrder")
class OrderDao(
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val orderId: Long? = null,
    val userId: Long,
    @Enumerated(STRING)
    var orderStatus: OrderStatus,
) {
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
    var orderItems: MutableList<OrderItemDao> = mutableListOf()

    fun addOrderItem(item: OrderItemDao) {
        orderItems.add(item)
        item.order = this
    }
}