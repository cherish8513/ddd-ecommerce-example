package com.example.ecommerce.common.infrastructure.util

object ChosungConverter {
    private const val HANGUL_START = 0xAC00
    private const val HANGUL_END = 0xD7A3
    private const val HANGUL_BASE = 0xAC00
    private const val CHOSUNG_BASE = 588

    private val CHOSUNG = arrayOf(
        'ㄱ', 'ㄲ', 'ㄴ', 'ㄷ', 'ㄸ', 'ㄹ', 'ㅁ', 'ㅂ', 'ㅃ',
        'ㅅ', 'ㅆ', 'ㅇ', 'ㅈ', 'ㅉ', 'ㅊ', 'ㅋ', 'ㅌ', 'ㅍ', 'ㅎ'
    )

    fun toChosung(koreanString: String): String {
        return koreanString.map { char ->
            if (char.code in HANGUL_START..HANGUL_END) {
                val chosungIndex = (char.code - HANGUL_BASE) / CHOSUNG_BASE
                CHOSUNG[chosungIndex]
            } else {
                char
            }
        }.joinToString("")
    }
}