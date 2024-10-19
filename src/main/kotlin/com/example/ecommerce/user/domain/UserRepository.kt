package com.example.ecommerce.product.domain

import com.example.ecommerce.user.domain.Role
import org.springframework.stereotype.Repository

@Repository
interface UserRepository {
    fun save(newUser: NewUser): Long
    fun findByPhoneNumber(phoneNumber: String): User?
    fun findByRoleAndPhoneNumber(role: Role, phoneNumber: String): User?
}