package com.example.ecommerce.product.infrastructure.persistence.user.repository

import com.example.ecommerce.product.infrastructure.persistence.user.model.UserDao
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserJpaRepository: JpaRepository<UserDao, Long> {
    fun findByPhoneNumber(phoneNumber: String): UserDao?
}