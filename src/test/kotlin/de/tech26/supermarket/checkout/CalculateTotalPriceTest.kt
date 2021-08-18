package de.tech26.supermarket.checkout

import de.tech26.supermarket.checkout.domain.CalculateTotalPrice
import de.tech26.supermarket.checkout.domain.Product
import de.tech26.supermarket.checkout.domain.ProductRepository
import io.kotlintest.shouldBe
import io.mockk.every
import io.mockk.mockk
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class CalculateTotalPriceTest {

    private val productRepository = mockk<ProductRepository>()
    private lateinit var calculateTotalPrice: CalculateTotalPrice

    @BeforeEach
    fun setUp() {
        calculateTotalPrice = CalculateTotalPrice(productRepository)
    }

    @Test
    fun `should calculate the total price of sku's`() {
        val skus = listOf("A", "A", "B")

        every {
            productRepository.findProduct("A")
        } returns Product("A", BigDecimal.valueOf(2), "Product")
        every {
            productRepository.findProduct("B")
        } returns Product("B", BigDecimal.valueOf(3), "Product")

        val result = calculateTotalPrice.perform(skus)
        result shouldBe BigDecimal.valueOf(7)
    }
}