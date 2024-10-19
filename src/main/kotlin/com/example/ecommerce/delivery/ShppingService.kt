package com.example.ecommerce.delivery

import com.example.api.static.exception.CustomException
import com.example.api.static.exception.CustomExceptionType
import com.example.ecommerce.common.domain.helper.TransactionalLockHelper
import com.example.ecommerce.order.infrastructure.persistence.order.model.OrderStatus
import com.example.ecommerce.product.domain.ProductService.Companion.PRODUCT_LOCK_ID
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class ShppingService(
    private val transactionalLockHelper: TransactionalLockHelper
) {

    /**
     * 결제가 완료 된 물건의 재고를 감소시키고 배송 중 상태로 변경
     * 이벤트 기반으로 변경이 필요함
     * 구매자는 결제를 하고 결제 완료 이벤트 발생
     * 판매자는 이벤트를 수신하면 제품 수량을 차감시키고 배송 상태를 변경함
     *
     */
    fun approveDelivery(userId: Long, requestDeliveryDto: RequestDeliveryDto) {
        val payAfterOrders = orderRepository.findByUserIdAndPaymentId(
            userId = userId,
            paymentId = requestDeliveryDto.paymentId
        )
        if (!payAfterOrders.all { transactionalLockHelper.lockAndRegisterUnlock("$PRODUCT_LOCK_ID:${it.product.productId}") }) {
            throw CustomExceptionType.RETRY_REQUEST.toException()
        }

        val quantityMap = payAfterOrders.associateBy({ it.productId }, { it.quantity })
        val foundProducts = productRepository.findAllById(payAfterOrders.map { it.productId })
        val successDecreasedProductIds = mutableListOf<Long>()
        val failDecreasedProductIds = mutableListOf<Long>()
        foundProducts.forEach { product ->
            val productId = product.productId.assertNotNull()
            val quantity = quantityMap[productId].assertNotNull()
            try {
                product.decrease(quantity)
                successDecreasedProductIds.add(productId)
            } catch (e: CustomException) {
                logger.debug(e.message)
                failDecreasedProductIds.add(productId)
            }
        }

        orderRepository.modifyOrderStatusAndPaymentId(
            userId = deliveryRequestDto.userId,
            orderIds = payAfterOrders
                .filter { failDecreasedProductIds.contains(it.productId) }
                .map { it.orderId.assertNotNull() },
            orderStatus = OrderStatus.PREPARE_DELIVERY,
            paymentId = deliveryRequestDto.paymentId
        )
        if (failDecreasedProductIds.isNotEmpty()) {
            // 환불 로직
            failDecreasedProductIds.forEach {
                logger.info("Refund product id: $it, userId: ${deliveryRequestDto.userId}, quantity: ${quantityMap[it]}")
            }

            orderRepository.modifyOrderStatusAndPaymentId(
                userId = deliveryRequestDto.userId,
                orderIds = payAfterOrders
                    .filter { failDecreasedProductIds.contains(it.productId) }
                    .map { it.orderId.assertNotNull() },
                orderStatus = OrderStatus.PAY_CANCEL,
                paymentId = deliveryRequestDto.paymentId
            )
        }
    }
}