package com.example.ecommerce.user.presentation.dto

import com.example.ecommerce.user.domain.Role
import jakarta.validation.constraints.Pattern

data class AddUserDto(
    @field:Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "휴대폰 번호는 010-xxxx-xxxx 형태로 입력해주세요.")
    val phoneNumber: String,
    val password: String,
    val role: Role,
)

data class RequestUserLoginDto(
    @field:Pattern(regexp = "^010-\\d{4}-\\d{4}$", message = "휴대폰 번호는 010-xxxx-xxxx 형태로 입력해주세요.")
    val phoneNumber: String,
    val password: String,
    val role: Role
)

class UserSessionDto(
    val userId: Long,
    val cartId: Long,
    val phoneNumber: String,
    val role: Role,
)