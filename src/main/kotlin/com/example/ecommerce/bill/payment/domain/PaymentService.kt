package com.example.ecommerce.bill.payment.domain

import com.example.ecommerce.common.domain.helper.TransactionalLockHelper
import com.example.ecommerce.order.infrastructure.persistence.order.model.OrderStatus
import com.example.ecommerce.payment.presentation.dto.PaymentDto
import com.example.ecommerce.payment.presentation.dto.RequestPaymentDto
import com.example.ecommerce.sales.order.OrderRepository
import com.example.ecommerce.sales.product.ProductRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class PaymentService(
    private val orderRepository: OrderRepository,
    private val paymentRepository: PaymentRepository,
    private val productRepository: ProductRepository,
    private val transactionalLockHelper: TransactionalLockHelper
) {
    fun requestPayment(userId: Long, requestPaymentDto: RequestPaymentDto): PaymentDto {
        val paymentId = paymentRepository.save(
            NewPayment(
                userId = userId,
                paymentMethod = requestPaymentDto.paymentMethod,
            )
        )

        orderRepository.modifyOrderStatusAndPaymentId(
            userId = userId,
            orderIds = requestPaymentDto.orderIds,
            orderStatus = OrderStatus.PAY_AFTER,
            paymentId = paymentId
        )

        return PaymentDto(paymentId = paymentId)
    }
}
