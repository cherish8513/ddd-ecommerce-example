package com.example.ecommerce.warehouse.product

import com.example.ecommerce.warehouse.product.presentation.dto.GetProductPageDto
import org.springframework.stereotype.Repository

@Repository
interface ProductRepository {
    fun save(newProduct: NewProduct): Long
    fun update(product: Product)
    fun delete(userId: Long, productId: Long)
    fun findByUserIdAndProductId(userId: Long, productId: Long): Product?
    fun findProductsByCondition(userId: Long, getProductPageDto: GetProductPageDto): List<Product>
}