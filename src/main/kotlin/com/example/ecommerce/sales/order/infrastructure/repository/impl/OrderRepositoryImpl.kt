package com.example.ecommerce.sales.order.infrastructure.repository.impl

import com.example.ecommerce.common.presentation.util.assertNotNull
import com.example.ecommerce.sales.order.NewOrder
import com.example.ecommerce.sales.order.OrderRepository
import com.example.ecommerce.sales.order.infrastructure.model.OrderDao
import com.example.ecommerce.sales.order.infrastructure.model.OrderItemDao
import com.example.ecommerce.sales.order.infrastructure.model.OrderStatus
import com.example.ecommerce.sales.order.infrastructure.repository.OrderJpaRepository

class OrderRepositoryImpl(
    private val orderJpaRepository: OrderJpaRepository
) : OrderRepository {
    override fun save(newOrder: NewOrder): Long {
        return orderJpaRepository.save(
            OrderDao(
            userId = newOrder.userId,
            orderStatus = OrderStatus.PREPARE_DELIVERY
        ).apply {
            newOrder.orderItems.map {
                this.addOrderItem(
                    OrderItemDao(
                        productId = it.productId,
                        quantity = it.quantity
                    )
                )
            }
        }).orderId.assertNotNull()
    }

    override fun modifyOrderStatusAndPaymentId(
        userId: Long,
        orderIds: List<Long>,
        orderStatus: OrderStatus,
        paymentId: Long
    ) {
        orderJpaRepository.modifyOrderStatusAndPaymentId(userId, orderIds, orderStatus, paymentId)
    }
}