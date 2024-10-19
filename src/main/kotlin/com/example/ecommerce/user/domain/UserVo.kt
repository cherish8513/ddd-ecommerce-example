package com.example.ecommerce.user.domain

import com.example.ecommerce.common.infrastructure.ICode

enum class Role(
    override val description: String,
    override val code: Char
) : ICode {
    SELLER("판매자", '1'),
    BUYER("구매자", '2');
}
