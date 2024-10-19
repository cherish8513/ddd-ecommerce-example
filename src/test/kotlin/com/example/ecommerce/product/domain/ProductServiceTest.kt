package com.example.ecommerce.product.domain

import com.example.ecommerce.common.Size
import com.example.ecommerce.user.presentation.dto.AddUserDto
import com.example.ecommerce.product.presentation.dto.GetProductPageDto
import com.example.ecommerce.product.presentation.dto.SaveProductDto
import com.example.ecommerce.user.domain.UserService
import org.junit.jupiter.api.BeforeAll
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestInstance
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import kotlin.test.Ignore

@Ignore
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest
class ProductServiceTest {
    @Autowired
    lateinit var productService: ProductService
    @Autowired
    lateinit var userService: UserService

    @BeforeAll
    fun setup() {
        userService.addUser(AddUserDto("010-1234-1234", "1234"))
    }

    @Test
    fun saveProductTest() {
        productService.saveProduct(
            userId = 1L,
            saveProductDto = SaveProductDto(
                name = "사과",
                price = 1000L,
                description = "",
                barcode = "",
                expirationYmd = "20240101",
                size = Size.SMALL
            )
        )
    }

    @Test
    fun updateProductTest() {
        productService.saveProduct(
            userId = 1L,
            saveProductDto = SaveProductDto(
                name = "사과",
                price = 1000L,
                description = "",
                barcode = "",
                expirationYmd = "20240101",
                size = Size.SMALL
            )
        )

        productService.saveProduct(
            userId = 1L,
            saveProductDto = SaveProductDto(
                productId = 1L,
                name = "사과3",
                price = 10005L,
                description = "1",
                barcode = "",
                expirationYmd = "20240101",
                size = Size.SMALL
            )
        )

        println(productService.getProduct(1L, 1L))
    }

    @Test
    fun deleteProductTest() {
        productService.saveProduct(
            userId = 1L,
            saveProductDto = SaveProductDto(
                name = "사과",
                price = 1000L,
                description = "1",
                barcode = "",
                expirationYmd = "20240101",
                size = Size.SMALL
            )
        )
        productService.deleteProduct(1L, 1L)
    }

    @Test
    fun getProductPageTest() {
        productService.saveProduct(
            userId = 1L,
            saveProductDto = SaveProductDto(
                name = "사과",
                price = 1000L,
                description = "1",
                barcode = "",
                expirationYmd = "20240101",
                size = Size.SMALL
            )
        )
        productService.getProductPage(1L, GetProductPageDto())
    }
}