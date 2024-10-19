package com.example.ecommerce.product.infrastructure.persistence.user.repository.impl

import com.example.ecommerce.common.presentation.util.assertNotNull
import com.example.ecommerce.product.domain.NewUser
import com.example.ecommerce.product.domain.User
import com.example.ecommerce.product.domain.toUser
import com.example.ecommerce.product.infrastructure.persistence.user.repository.UserJpaRepository

class UserRepositoryImpl(
    private val userJpaRepository: UserJpaRepository
): UserRepository {
    override fun save(newUser: NewUser): Long {
        return userJpaRepository.save(newUser.toEntity()).userId.assertNotNull()
    }

    override fun findByPhoneNumber(phoneNumber: String): User? {
        return userJpaRepository.findByPhoneNumber(phoneNumber).toUser()
    }

}