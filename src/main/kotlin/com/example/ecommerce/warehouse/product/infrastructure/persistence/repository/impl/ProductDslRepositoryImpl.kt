package com.example.ecommerce.warehouse.product.infrastructure.persistence.repository.impl

import com.example.ecommerce.infrastructure.QTbProduct.tbProduct
import com.example.ecommerce.product.infrastructure.persistence.model.ProductDao
import com.example.ecommerce.product.presentation.dto.GetProductPageDto
import com.example.ecommerce.product.infrastructure.persistence.repository.ProductDslRepository
import com.querydsl.jpa.impl.JPAQueryFactory

class ProductDslRepositoryImpl(
    private val jpaQueryFactory: JPAQueryFactory
): ProductDslRepository {
    override fun findProductsByCondition(userId: Long, getProductPageDto: GetProductPageDto): List<ProductDao> {
        return jpaQueryFactory
            .select(tbProduct)
            .from(tbProduct)
            .where(
                tbProduct.userId.eq(userId),
                getProductPageDto.productId?.let { tbProduct.productId.gt(it) },
                getProductPageDto.nameCondition?.let {
                    tbProduct.name.contains(it).or(tbProduct.chosung.contains(it))
                }
            )
            .limit(getProductPageDto.limit)
            .fetch()
    }
}