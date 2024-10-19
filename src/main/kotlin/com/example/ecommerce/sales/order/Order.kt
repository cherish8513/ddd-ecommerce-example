package com.example.ecommerce.sales.order

import com.example.ecommerce.sales.order.infrastructure.model.OrderStatus

data class Order(
    val orderId: Long,
    val userId: Long,
    val orderItems: List<OrderItem>,
    val orderStatus: OrderStatus,
)

data class NewOrder(
    val userId: Long,
    val orderItems: List<OrderItem>,
)