package com.example.ecommerce.mvc.security

import com.example.ecommerce.common.security.JwtTokenService
import com.example.ecommerce.product.infrastructure.persistence.user.model.UserDao
import com.example.ecommerce.product.infrastructure.persistence.user.repository.UserJpaRepository
import com.example.ecommerce.shouldBe
import org.junit.jupiter.api.Test
import org.mockito.kotlin.given
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import kotlin.test.Ignore

@Ignore
@SpringBootTest
class JwtTokenServiceTest {
    @Autowired
    lateinit var jwtTokenService: JwtTokenService

    @MockBean
    lateinit var userJpaRepository: UserJpaRepository

    @Test
    fun generateToken() {
        println(jwtTokenService.generateToken("010-1234-1234"))
    }

    @Test
    fun extractUser() {
        // given
        val token = jwtTokenService.generateToken("010-1234-1234")
        given(userJpaRepository.findByPhoneNumber("010-1234-1234"))
            .willReturn(UserDao(userId = 1L, phoneNumber = "010-1234-1234", encryptedPassword = "a"))

        // when
        val userSession = jwtTokenService.extractUser(token)

        // then
        userSession.userId shouldBe 1L
    }

    @Test
    fun addBlackList() {
        // given
        val token = jwtTokenService.generateToken("010-1234-1234")

        // when
        val result = jwtTokenService.addBlackList(token)

        // then
        result shouldBe true
    }

    @Test
    fun `cant extract user in black list`() {
        // given
        val token = jwtTokenService.generateToken("010-1234-1234")
        jwtTokenService.addBlackList(token)

        // when
        val userSession = jwtTokenService.extractUser(token)

        // then
        userSession shouldBe null
    }
}