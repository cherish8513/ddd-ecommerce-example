package com.example.ecommerce.sales.product

import com.example.ecommerce.buyer.product.domain.Product
import com.example.ecommerce.common.Size
import com.example.ecommerce.product.infrastructure.persistence.model.ProductDao
import com.example.ecommerce.common.presentation.util.assertNotNull

data class Product(
    val productId: Long,
    val userId: Long,
    val name: String,
    val price: Long,
    val description: String,
    val barcode: String,
    val expirationYmd: String,
    val size: Size
)

data class NewProduct(
    val userId: Long,
    val name: String,
    val price: Long,
    val description: String,
    val barcode: String,
    val expirationYmd: String,
    val size: Size
) {
    fun toEntity(): ProductDao {
        return ProductDao(
            userId = userId,
            name = name,
            price = price,
            description = description,
            barcode = barcode,
            expirationYmd = expirationYmd,
            size = size
        )
    }
}

fun ProductDao.toProduct(): Product {
    return com.example.ecommerce.seller.product.domain.Product(
        productId = this.productId.assertNotNull(),
        userId = userId,
        name = name,
        price = price,
        description = description,
        barcode = barcode,
        expirationYmd = expirationYmd,
        size = size
    )
}