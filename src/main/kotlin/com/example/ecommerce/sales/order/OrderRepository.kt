package com.example.ecommerce.sales.order

import com.example.ecommerce.sales.order.infrastructure.model.OrderStatus
import org.springframework.stereotype.Repository

@Repository
interface OrderRepository {
    fun save(newOrder: NewOrder): Long
    fun modifyOrderStatusAndPaymentId(userId: Long, orderIds: List<Long>, orderStatus: OrderStatus, paymentId: Long)
}