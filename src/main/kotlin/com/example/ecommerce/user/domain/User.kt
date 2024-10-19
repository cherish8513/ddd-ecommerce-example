package com.example.ecommerce.product.domain

import com.example.ecommerce.common.presentation.util.assertNotNull
import com.example.ecommerce.product.infrastructure.persistence.user.model.UserDao
import com.example.ecommerce.user.domain.Role

data class User (
    val userId: Long,
    val phoneNumber: String,
    val encryptedPassword: String,
    val role: Role,
)

data class NewUser(
    val phoneNumber: String,
    val encryptedPassword: String,
    val role: Role,
) {
    fun toEntity(): UserDao {
        return UserDao(
            phoneNumber = phoneNumber,
            encryptedPassword = encryptedPassword,
            role = role
        )
    }
}

fun UserDao?.toUser(): User? {
    return if (this != null) {
        User(
            userId = this.userId.assertNotNull(),
            phoneNumber = this.phoneNumber,
            encryptedPassword = this.encryptedPassword,
            role = this.role
        )
    }
    else null
}