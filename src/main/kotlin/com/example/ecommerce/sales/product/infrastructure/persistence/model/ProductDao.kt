package com.example.ecommerce.sales.product.infrastructure.persistence.model

import com.example.ecommerce.warehouse.product.infrastructure.persistence.model.Size
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
    var name: String,
    var price: Int,
    var description: String,
    var barcode: String,
    var expirationYmd: String,
    @Enumerated(STRING)
    var size: Size
)