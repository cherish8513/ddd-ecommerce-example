package com.example.ecommerce.warehouse.product.infrastructure.persistence.repository.impl

import com.example.ecommerce.common.presentation.util.assertNotNull
import com.example.ecommerce.buyer.product.domain.NewProduct
import com.example.ecommerce.buyer.product.domain.Product
import com.example.ecommerce.product.domain.ProductRepository
import com.example.ecommerce.buyer.product.domain.toProduct
import com.example.ecommerce.product.infrastructure.persistence.repository.ProductJpaRepository
import com.example.ecommerce.product.presentation.dto.GetProductPageDto

class ProductRepositoryImpl(
    private val productJpaRepository: ProductJpaRepository
): ProductRepository {
    override fun save(newProduct: NewProduct): Long {
        return productJpaRepository.save(newProduct.toEntity()).productId.assertNotNull()
    }

    override fun update(product: Product) {
        productJpaRepository
            .findByUserIdAndProductId(userId = product.userId, productId = product.productId)
            .assertNotNull()
            .apply {
                name = product.name
                price = product.price
                barcode = product.barcode
                size = product.size
                description = product.description
                expirationYmd = product.expirationYmd

            }

    }

    override fun delete(userId: Long, productId: Long) {
        val product = productJpaRepository.findByUserIdAndProductId(userId, productId).assertNotNull()
        productJpaRepository.delete(product)
    }

    override fun findByUserIdAndProductId(userId: Long, productId: Long): Product? {
        return productJpaRepository.findByUserIdAndProductId(userId, productId)?.toProduct()
    }

    override fun findProductsByCondition(userId: Long, getProductPageDto: GetProductPageDto): List<Product> {
        return productJpaRepository.findProductsByCondition(userId, getProductPageDto).map { it.toProduct() }
    }
}