package com.example.ecommerce.common.infrastructure

interface ICode {
    val description: String
    val code: Char
}

enum class Yn {
    Y, N
}