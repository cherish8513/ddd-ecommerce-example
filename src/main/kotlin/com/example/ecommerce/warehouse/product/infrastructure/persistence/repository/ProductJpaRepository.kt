package com.example.ecommerce.warehouse.product.infrastructure.persistence.repository

import com.example.ecommerce.warehouse.product.infrastructure.persistence.model.ProductDao
import com.example.ecommerce.warehouse.product.presentation.dto.GetProductPageDto
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductJpaRepository: JpaRepository<ProductDao, Long>, ProductDslRepository {
    fun findByUserIdAndProductId(userId: Long, productId: Long): ProductDao?
}

@Repository
interface ProductDslRepository {
    fun findProductsByCondition(userId: Long, getProductPageDto: GetProductPageDto): List<ProductDao>
}
