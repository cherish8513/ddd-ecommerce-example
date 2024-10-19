package com.example.ecommerce.product.infrastructure.persistence.product.repository.impl

import com.example.ecommerce.product.domain.MockProduct
import com.example.ecommerce.product.presentation.dto.GetProductPageDto
import com.example.ecommerce.shouldBe
import org.junit.jupiter.api.DisplayName
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Ignore
import kotlin.test.Test

@Ignore
@SpringBootTest
class ProductRepositoryImplTest {
    @Autowired
    lateinit var productRepository: ProductRepository

    @Test
    @DisplayName("초성 검색 테스트")
    fun findProductsByConditionByChoseong() {
        // given
        productRepository.save(MockProduct.generate(name = "발렌타인초콜릿"))
        productRepository.save(MockProduct.generate(name = "발렌타인데이"))
        productRepository.save(MockProduct.generate(name = "발렌타인"))
        productRepository.save(MockProduct.generate(name = "발렌타인와인"))

        // when
        val result = productRepository.findProductsByCondition(1L,
            GetProductPageDto(nameCondition = "ㅌㅇ")
        )

        // then
        result.size shouldBe 4
        result.forEach {
            println("name: ${it.name}")
        }
    }

    @Test
    @DisplayName("like 검색 테스트")
    fun findProductsByConditionWithLike() {
        // given
        productRepository.save(MockProduct.generate(name = "발렌타인초콜릿"))
        productRepository.save(MockProduct.generate(name = "발렌타인데이"))
        productRepository.save(MockProduct.generate(name = "발렌타인"))
        productRepository.save(MockProduct.generate(name = "발렌타인와인"))

        // when
        val result = productRepository.findProductsByCondition(1L,
            GetProductPageDto(nameCondition = "타인")
        )

        // then
        result.size shouldBe 4
        result.forEach {
            println("name: ${it.name}")
        }
    }
}