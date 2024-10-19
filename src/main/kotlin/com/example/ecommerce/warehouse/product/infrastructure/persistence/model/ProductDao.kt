package com.example.ecommerce.warehouse.product.infrastructure.persistence.model

import com.example.ecommerce.common.infrastructure.util.ChosungConverter
import com.querydsl.core.annotations.QueryEntity
import jakarta.persistence.*
import jakarta.persistence.EnumType.STRING
import jakarta.persistence.GenerationType.IDENTITY
import org.hibernate.annotations.DynamicUpdate

@Entity
@QueryEntity
@DynamicUpdate
@Table(name = "TbProduct")
class ProductDao (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    val productId: Long? = null,
    val userId: Long,
    var name: String,
    var chosung: String,
    var price: Int,
    var description: String,
    var barcode: String,
    var expirationYmd: String,
    @Enumerated(STRING)
    var size: Size
) {
    constructor(userId: Long, name: String, price: Int, description: String, barcode: String, expirationYmd: String, size: Size): this(
        userId = userId,
        name = name,
        chosung = ChosungConverter.toChosung(name),
        price = price,
        description = description,
        barcode = barcode,
        expirationYmd = expirationYmd,
        size = size
    )
}