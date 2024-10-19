package com.example.ecommerce.sales.product

import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class ProductValidationService(
    private val productRepository: ProductRepository
) {
    fun isExistProduct(productId: Long): Boolean {
        return productRepository.isExist(productId)
    }
}