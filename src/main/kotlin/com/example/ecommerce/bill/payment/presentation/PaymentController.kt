package com.example.ecommerce.bill.payment.presentation

import com.example.ecommerce.bill.payment.domain.PaymentService
import com.example.ecommerce.payment.presentation.dto.PaymentDto
import com.example.ecommerce.payment.presentation.dto.RequestPaymentDto
import com.example.ecommerce.common.presentation.util.dto.ResponseDto
import com.example.ecommerce.user.presentation.dto.UserSessionDto
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/bills")
class PaymentController(
    private val paymentService: PaymentService
) {
    @PostMapping
    fun requestPayment(@RequestBody requestPaymentDto: RequestPaymentDto, @AuthenticationPrincipal userSessionDto: UserSessionDto): ResponseDto<PaymentDto> {
        return ResponseDto(paymentService.requestPayment(userSessionDto.userId, requestPaymentDto))
    }
}