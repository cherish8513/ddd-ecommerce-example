package com.example.api.static.exception

import org.springframework.http.HttpStatus

class CustomException(val exceptionType: CustomExceptionType, message: String?) : RuntimeException(message)

enum class CustomExceptionType(
    val httpStatus: HttpStatus,
    val message: String = "알 수 없는 에러가 발생했습니다."
) {
    UNEXPECTED_ERROR_OCCURRED(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러가 발생했습니다."),
    DUPLICATE_USER_EXIST(HttpStatus.FORBIDDEN, "중복된 휴대폰 번호가 존재합니다."),
    INVALID_PARAMETER(HttpStatus.FORBIDDEN, "허용되지 않는 요청 값입니다."),
    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "사용자를 찾을 수 없습니다."),
    LOGIN_REQUIRED(HttpStatus.UNAUTHORIZED, "로그인 후 이용할 수 있습니다."),
    RETRY_REQUEST(HttpStatus.FORBIDDEN, "요청이 지연되고 있습니다. 잠시 후 다시 시도해주세요."),
    STOCK_NOT_NEGATIVE(HttpStatus.FORBIDDEN);

    fun toException(message: String? = null): CustomException {
        return CustomException(this, message ?: this.message)
    }
}