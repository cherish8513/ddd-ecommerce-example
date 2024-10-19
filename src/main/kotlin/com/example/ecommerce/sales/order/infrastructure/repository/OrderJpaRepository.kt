package com.example.ecommerce.sales.order.infrastructure.repository

import com.example.ecommerce.sales.order.infrastructure.model.OrderDao
import com.example.ecommerce.sales.order.infrastructure.model.OrderStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface OrderJpaRepository : JpaRepository<OrderDao, Long>,
    DslOrderRepository {
    @Modifying
    @Query(
        """
        UPDATE TbOrder o
        SET o.orderStatus = :orderStatus, o.paymentId = :paymentId
        WHERE o.userId = :userId
        AND o.orderId IN :orderIds
    """
    )
    fun modifyOrderStatusAndPaymentId(userId: Long, orderIds: List<Long>, orderStatus: OrderStatus, paymentId: Long)
}

@Repository
interface DslOrderRepository