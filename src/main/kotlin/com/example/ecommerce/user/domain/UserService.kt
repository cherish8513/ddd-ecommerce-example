package com.example.ecommerce.user.domain

import com.example.api.static.exception.CustomExceptionType
import com.example.ecommerce.common.presentation.util.assertNotNull
import com.example.ecommerce.common.security.JwtTokenService
import com.example.ecommerce.product.domain.NewUser
import com.example.ecommerce.product.domain.UserRepository
import com.example.ecommerce.user.presentation.dto.AddUserDto
import com.example.ecommerce.user.presentation.dto.RequestUserLoginDto
import com.example.ecommerce.user.presentation.dto.SessionDto
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class UserService(
    private val userRepository: UserRepository,
    private val passwordEncoder: PasswordEncoder,
    private val jwtTokenService: JwtTokenService,
) {
    fun addUser(addUserDto: AddUserDto) {
        if(userRepository.findByPhoneNumber(addUserDto.phoneNumber) != null) {
            throw CustomExceptionType.DUPLICATE_USER_EXIST.toException()
        }
        userRepository.save(
            NewUser(
                phoneNumber = addUserDto.phoneNumber,
                encryptedPassword = passwordEncoder.encode(addUserDto.password),
                role = addUserDto.role
            )
        )
    }

    fun loginUser(requestUserLoginDto: RequestUserLoginDto): SessionDto {
        val loginUser = userRepository.findByRoleAndPhoneNumber(requestUserLoginDto.role, requestUserLoginDto.phoneNumber).assertNotNull(CustomExceptionType.NOT_FOUND_USER)
        if (!passwordEncoder.matches(requestUserLoginDto.password, loginUser.encryptedPassword)) {
            throw CustomExceptionType.NOT_FOUND_USER.toException()
        }
        return SessionDto(
            jwtToken = jwtTokenService.generateToken(requestUserLoginDto.phoneNumber)
        )
    }

    fun logoutUser(jwtToken: String) {
        jwtTokenService.addBlackList(jwtToken)
    }
}