package com.example.ecommerce.product.infrastructure.persistence.user.model

import com.example.ecommerce.user.domain.Role
import jakarta.persistence.*
import jakarta.persistence.GenerationType.IDENTITY

@Entity
@Table(name = "TbUser")
class UserDao (
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column
    val userId: Long? = null,
    @Column(length = 20)
    var phoneNumber: String,
    @Column(length = 100)
    var encryptedPassword: String,
    val role: Role
)