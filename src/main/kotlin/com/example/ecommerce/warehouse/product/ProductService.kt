package com.example.ecommerce.warehouse.product

import com.example.ecommerce.common.presentation.util.assertNotNull
import com.example.ecommerce.common.presentation.util.dto.PageDto
import com.example.ecommerce.common.presentation.util.toPageDto
import com.example.ecommerce.warehouse.product.presentation.dto.GetProductPageDto
import com.example.ecommerce.warehouse.product.presentation.dto.ProductDto
import com.example.ecommerce.warehouse.product.presentation.dto.SaveProductDto
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(rollbackFor = [Exception::class])
@Service
class ProductService(
    private val productRepository: ProductRepository
) {
    fun saveProduct(userId: Long, saveProductDto: SaveProductDto) {
        if (saveProductDto.productId != null) {
            productRepository.update(
                Product(
                    productId = saveProductDto.productId.assertNotNull(),
                    userId = userId,
                    name = saveProductDto.name,
                    price = saveProductDto.price,
                    barcode = saveProductDto.barcode,
                    size = saveProductDto.size,
                    description = saveProductDto.description,
                    expirationYmd = saveProductDto.expirationYmd
                )
            )
        } else {
            productRepository.save(
                NewProduct(
                    userId = userId,
                    name = saveProductDto.name,
                    price = saveProductDto.price,
                    barcode = saveProductDto.barcode,
                    size = saveProductDto.size,
                    description = saveProductDto.description,
                    expirationYmd = saveProductDto.expirationYmd
                )
            )
        }
    }

    fun deleteProduct(userId: Long, productId: Long) {
        productRepository.delete(userId, productId)
    }

    fun getProductPage(userId: Long, getProductPageDto: GetProductPageDto): PageDto<List<ProductDto>> {
        val products = productRepository.findProductsByCondition(userId, getProductPageDto)
        return products.map {
            ProductDto(
                productId = it.productId,
                name = it.name,
                price = it.price,
                description = it.description,
                barcode = it.barcode,
                expirationYmd = it.expirationYmd,
                size = it.size
            )
        }.toPageDto(products.size < getProductPageDto.limit)
    }

    fun getProduct(userId: Long, productId: Long): ProductDto {
        return productRepository.findByUserIdAndProductId(userId, productId)
            .assertNotNull()
            .let {
                ProductDto(
                    productId = it.productId,
                    name = it.name,
                    price = it.price,
                    description = it.description,
                    barcode = it.barcode,
                    expirationYmd = it.expirationYmd,
                    size = it.size
                )
            }
    }

    companion object {
        const val PRODUCT_LOCK_ID = "BILL:PRODUCT:LOCK"
    }
}