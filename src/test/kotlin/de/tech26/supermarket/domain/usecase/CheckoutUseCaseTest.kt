package de.tech26.supermarket.domain.usecase

import arrow.core.right
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.math.BigDecimal

internal class CheckoutUseCaseTest {

    private val checkout: CheckoutUseCase = CheckoutUseCase()

    @Test
    internal fun `should calculate checkout price for an empty cart`() {
        val result = checkout(emptyList())

        assertThat(result).isEqualTo(BigDecimal.ZERO.right())
    }
}
