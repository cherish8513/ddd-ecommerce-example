package com.example.ecommerce.common.config.filter

import com.example.api.static.exception.CustomException
import com.example.api.static.exception.CustomExceptionType.LOGIN_REQUIRED
import com.example.ecommerce.common.presentation.util.dto.Meta
import com.example.ecommerce.common.presentation.util.dto.ResponseDto
import com.example.ecommerce.common.security.JwtTokenService
import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtFilter(private val jwtTokenService: JwtTokenService) : OncePerRequestFilter() {

    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        try {
            val authHeader = request.getHeader("Authorization")
            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                val user = jwtTokenService.extractUser(authHeader.substring(7))
                if (SecurityContextHolder.getContext().authentication == null) {
                    val authToken = UsernamePasswordAuthenticationToken(user, null, emptyList())
                    authToken.details = WebAuthenticationDetailsSource().buildDetails(request)
                    SecurityContextHolder.getContext().authentication = authToken
                }
            }
            filterChain.doFilter(request, response)
        } catch (e: CustomException) {
            if(e.exceptionType == LOGIN_REQUIRED) {
                response.contentType = "application/json;charset=UTF-8"
                response.status = LOGIN_REQUIRED.httpStatus.value()

                response.writer.write(ObjectMapper().writeValueAsString(
                    ResponseDto(
                    meta = Meta(
                        code = LOGIN_REQUIRED.httpStatus.value().toString(),
                        message = LOGIN_REQUIRED.message,
                        data = null
                    )
                )
                ))
            } else {
                throw e
            }
        }
    }
}