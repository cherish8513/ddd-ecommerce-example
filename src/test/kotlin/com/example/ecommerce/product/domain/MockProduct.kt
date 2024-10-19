package com.example.ecommerce.product.domain

import com.example.ecommerce.buyer.product.domain.NewProduct
import com.example.ecommerce.common.Size

object MockProduct{
    fun generate(
        userId: Long = 1L,
        name: String,
        price: Long = 1000,
        description: String = "설명",
        barcode: String = "바코드",
        expirationYmd: String = "20240101",
        size: Size = Size.LARGE): NewProduct {

        return NewProduct(
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