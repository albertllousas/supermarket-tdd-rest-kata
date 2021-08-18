package de.tech26.supermarket.checkout

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
        calculateTotalPrice = CalculateTotalPrice()
    }

    @Test
    fun `should calculate the total price of sku's`() {
        val skus = listOf("A", "A", "B")
        every {
            productRepository.findProduct(any<String>())
        } returns Product("A", BigDecimal.valueOf(2), "Product")
        val result = calculateTotalPrice.perform(skus)

        result shouldBe BigDecimal.valueOf(6)
    }
}