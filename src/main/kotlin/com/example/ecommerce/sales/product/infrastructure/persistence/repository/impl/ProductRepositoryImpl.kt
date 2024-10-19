package com.example.ecommerce.sales.product.infrastructure.persistence.repository.impl

import com.example.ecommerce.sales.product.ProductRepository
import com.example.ecommerce.sales.product.infrastructure.persistence.repository.ProductJpaRepository

class ProductRepositoryImpl(
    private val productJpaRepository: ProductJpaRepository
) : ProductRepository {
    override fun isExist(productId: Long): Boolean {
        return productJpaRepository.existsByProductId(productId)
    }
}