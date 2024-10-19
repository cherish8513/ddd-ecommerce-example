package com.example.ecommerce.common.security

import com.example.api.static.exception.CustomExceptionType
import com.example.ecommerce.common.presentation.util.assertNotNull
import com.example.ecommerce.product.domain.UserRepository
import com.example.ecommerce.user.presentation.dto.UserSessionDto
import io.jsonwebtoken.Jwts
import io.jsonwebtoken.io.Decoders
import io.jsonwebtoken.security.Keys
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Service
import java.time.Duration
import java.util.*
import javax.crypto.SecretKey


@Service
class JwtTokenService(
    private val redisTemplate: RedisTemplate<String, String>,
    private val userRepository: UserRepository
) {
    private val expiration = 60 * 60 * 1000 * 24 * 7 // 7 days
    private val signingKey: SecretKey = getSigningKey()

    private fun getSigningKey(): SecretKey {
        val secretKey = "9a4f2c8d3b7a1e6f45c8a0b3f267d8b1d4e6f3c8a9d2b5f8e3a9c8b5f6v8a3d9"
        val keyBytes = Decoders.BASE64.decode(secretKey)
        return Keys.hmacShaKeyFor(keyBytes)
    }

    fun generateToken(subject: String): String {
        return Jwts.builder()
            .signWith(signingKey)
            .subject(subject)
            .issuedAt(Date())
            .expiration(Date(System.currentTimeMillis() + expiration))
            .compact()
    }

    fun extractUser(token: String): UserSessionDto {
        if (redisTemplate.opsForValue().get(token) != null) {
            throw CustomExceptionType.LOGIN_REQUIRED.toException()
        }
        val phoneNumber = Jwts.parser().verifyWith(signingKey).build().parseSignedClaims(token).payload.subject
        val user = userRepository.findByPhoneNumber(phoneNumber).assertNotNull(CustomExceptionType.LOGIN_REQUIRED)
        return UserSessionDto(
            userId = user.userId.assertNotNull(),
            phoneNumber = user.phoneNumber,
            role = user.role
        )
    }

    fun addBlackList(token: String): Boolean {
        return redisTemplate.opsForValue().setIfAbsent(token, "", Duration.ofSeconds(expiration.toLong())) == true
    }
}