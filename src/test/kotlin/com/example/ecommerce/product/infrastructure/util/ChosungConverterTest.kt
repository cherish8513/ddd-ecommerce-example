package com.example.ecommerce.product.infrastructure.util

import com.example.ecommerce.shouldBe
import org.junit.jupiter.api.Test

class ChosungConverterTest {
    @Test
    fun toChosung() {
        // given
        val input = "곡괭이"
        val input2 = "엘레베이터"

        // when
        val result = ChosungConverter.toChosung(input)
        val result2 = ChosungConverter.toChosung(input2)

        // then
        result shouldBe "ㄱㄱㅇ"
        result2 shouldBe "ㅇㄹㅂㅇㅌ"
    }
}