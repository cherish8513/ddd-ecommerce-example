package com.example.ecommerce.user.presentation

import com.example.ecommerce.user.presentation.dto.AddUserDto
import com.example.ecommerce.user.presentation.dto.RequestUserLoginDto
import com.example.ecommerce.common.presentation.ApiController
import com.example.ecommerce.common.presentation.util.dto.ResponseDto
import com.example.ecommerce.user.presentation.dto.SessionDto
import com.example.ecommerce.user.domain.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader
import org.springframework.web.bind.annotation.RequestMapping

@ApiController
@RequestMapping("/users")
class UserController(
    private val userService: UserService
) {
    @PostMapping("/register")
    fun addUser(@Valid @RequestBody addUserDto: AddUserDto): ResponseDto<Unit> {
        return ResponseDto(userService.addUser(addUserDto))
    }

    @PostMapping("/login")
    fun loginUser(@Valid @RequestBody requestUserLoginDto: RequestUserLoginDto): ResponseDto<SessionDto> {
        return ResponseDto(userService.loginUser(requestUserLoginDto))
    }

    @PostMapping("/logout")
    fun logoutUser(@RequestHeader("Authorization") token: String): ResponseDto<Unit> {
        val jwtToken = token.substringAfter(" ")
        return ResponseDto(userService.logoutUser(jwtToken))
    }
}