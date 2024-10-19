package com.example.ecommerce.sales.product.infrastructure.persistence.repository


import com.example.ecommerce.sales.product.infrastructure.persistence.model.ProductDao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ProductJpaRepository: JpaRepository<ProductDao, Long> {
    fun existsByProductId(productId: Long): Boolean
}