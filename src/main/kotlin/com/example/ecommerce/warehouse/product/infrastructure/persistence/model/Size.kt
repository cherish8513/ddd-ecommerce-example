package com.example.ecommerce.warehouse.product.infrastructure.persistence.model

import com.example.ecommerce.common.infrastructure.ICode

enum class Size(
    override val description: String,
    override val code: Char
) : ICode {
    SMALL("작은 사이즈", '1'),
    LARGE("큰 사이즈", '2');
}