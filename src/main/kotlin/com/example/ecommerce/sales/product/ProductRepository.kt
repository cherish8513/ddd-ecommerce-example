package com.example.ecommerce.sales.product

import org.springframework.stereotype.Repository

@Repository
interface ProductRepository {
    fun isExist(productId: Long): Boolean
}