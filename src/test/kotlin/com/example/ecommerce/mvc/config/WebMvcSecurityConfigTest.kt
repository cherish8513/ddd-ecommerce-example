package com.example.ecommerce.mvc.config

import com.example.ecommerce.common.security.JwtTokenService
import com.example.ecommerce.user.presentation.dto.RequestUserLoginDto
import com.example.ecommerce.user.presentation.dto.UserSessionDto
import com.fasterxml.jackson.databind.ObjectMapper
import com.google.common.net.HttpHeaders
import org.junit.jupiter.api.Test
import org.mockito.BDDMockito.*
import org.mockito.kotlin.any
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import kotlin.test.Ignore

@Ignore
@WebMvcTest(UserController::class)
class WebMvcSecurityConfigTest {

    @Autowired
    private lateinit var mockMvc: MockMvc

    @MockBean
    private lateinit var jwtTokenService: JwtTokenService

    @MockBean
    private lateinit var userService: UserService

    @Test
    fun `unauthenticated access to excluded path should be allowed`() {
        // given
        given(userService.loginUser(any())).willReturn(SessionDto("1234"))

        // when & then
        mockMvc.perform(
            post("/api/users/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(
                    ObjectMapper().writeValueAsString(
                        RequestUserLoginDto(
                            phoneNumber = "010-1234-1234",
                            password = "1234"
                        )
                    )
                )
        ).andExpect(status().isOk)
    }

    @Test
    fun `unauthenticated access to protected path should be forbidden`() {
        mockMvc.perform(post("/api/users/logout").header(HttpHeaders.AUTHORIZATION, "Bearer Anything"))
            .andExpect(status().isUnauthorized)
    }

    @Test
    fun `authenticated access to protected path should be allowed`() {
        // given
        val token = "1234"
        given(jwtTokenService.extractUser(token)).willReturn(UserSessionDto(userId = 1L, phoneNumber = "010-1234-1234"))
        willDoNothing().given(userService).logoutUser(anyString())

        //when & then
        mockMvc.perform(post("/api/users/logout").header(HttpHeaders.AUTHORIZATION, "Bearer $token"))
            .andExpect(status().isOk)
    }
}