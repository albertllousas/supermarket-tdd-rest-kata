package de.tech26.supermarket.domain.model

import arrow.core.right
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CartTest {

    @Test
    internal fun `should calculate cart price`() {
        val cart = Cart(listOf(Sku("A"), Sku("B"), Sku("A")))
        val result = cart.calculatePrice(
            listOf(
                Item(Sku("A"), 2.0.toBigDecimal())
            ),
            Item(
                Sku("B"), 4.0.toBigDecimal()
            )
        )

        assertThat(result).isEqualTo(8.0.toBigDecimal().right())
    }
}
