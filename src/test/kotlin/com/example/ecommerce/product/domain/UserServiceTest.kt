package com.example.ecommerce.product.domain

import com.example.api.static.exception.CustomExceptionType
import com.example.ecommerce.user.presentation.dto.AddUserDto
import com.example.ecommerce.user.presentation.dto.RequestUserLoginDto
import com.example.ecommerce.shouldBe
import com.example.ecommerce.user.domain.UserService
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Ignore

@Ignore
@SpringBootTest
class UserServiceTest {

    @Autowired
    lateinit var userService: UserService

    @Test
    fun `success loginUser`() {
        // given
        userService.addUser(AddUserDto("010-1234-1234", "1234"))

        // when
        val session = userService.loginUser(RequestUserLoginDto("010-1234-1234", "1234"))

        // then
        session.jwtToken.isNotBlank() shouldBe true
    }

    @Test
    fun `fail loginUser`() {
        // given
        userService.addUser(AddUserDto("010-1234-1234", "1234"))

        try {
        // when
            userService.loginUser(RequestUserLoginDto("010-1234-1234", "123"))
        } catch (e: Exception) {
        // then
            e.message shouldBe CustomExceptionType.NOT_FOUND_USER.toException().message
        }
    }
}