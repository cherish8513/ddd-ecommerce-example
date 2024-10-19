package com.example.ecommerce.warehouse.product.presentation

import com.example.ecommerce.common.presentation.ApiController
import com.example.ecommerce.common.presentation.util.dto.PageDto
import com.example.ecommerce.common.presentation.util.dto.ResponseDto
import com.example.ecommerce.user.presentation.dto.UserSessionDto
import com.example.ecommerce.warehouse.product.ProductService
import com.example.ecommerce.warehouse.product.presentation.dto.GetProductPageDto
import com.example.ecommerce.warehouse.product.presentation.dto.ProductDto
import com.example.ecommerce.warehouse.product.presentation.dto.SaveProductDto
import jakarta.validation.Valid
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.web.bind.annotation.*

@ApiController
@RequestMapping("/products")
class ProductController(
    private val productService: ProductService
) {
    @PostMapping
    fun saveProduct(@Valid @RequestBody saveProductDto: SaveProductDto, @AuthenticationPrincipal userSessionDto: UserSessionDto): ResponseDto<Unit> {
        return ResponseDto(
            productService.saveProduct(
                userId = userSessionDto.userId,
                saveProductDto = saveProductDto
            )
        )
    }

    @DeleteMapping
    fun deleteProduct(productId: Long, @AuthenticationPrincipal userSessionDto: UserSessionDto): ResponseDto<Unit> {
        return ResponseDto(
            productService.deleteProduct(
                userId = userSessionDto.userId,
                productId = productId
            )
        )
    }

    @GetMapping
    fun getProductPage(getProductPageDto: GetProductPageDto, @AuthenticationPrincipal userSessionDto: UserSessionDto): ResponseDto<PageDto<List<ProductDto>>> {
        return ResponseDto(
            productService.getProductPage(
                userId = userSessionDto.userId,
                getProductPageDto = getProductPageDto
            )
        )
    }

    @GetMapping("/{productId}")
    fun getProduct(@PathVariable productId: Long, @AuthenticationPrincipal userSessionDto: UserSessionDto): ResponseDto<ProductDto> {
        return ResponseDto(
            productService.getProduct(
                userId = userSessionDto.userId,
                productId = productId
            )
        )
    }
}