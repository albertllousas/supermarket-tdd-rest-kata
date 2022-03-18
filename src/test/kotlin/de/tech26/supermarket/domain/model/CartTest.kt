package de.tech26.supermarket.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class CartTest {

    @Test
    internal fun `should calculate cart price`() {
        val cart = Cart(
            listOf(
                Item(Sku("A"), 2.0.toBigDecimal()),
                Item(Sku("B"), 4.0.toBigDecimal()),
                Item(Sku("A"), 2.0.toBigDecimal())
            )
        )

        val result = cart.calculateTotal()

        assertThat(result).isEqualTo(8.0.toBigDecimal())
    }

    @Test
    internal fun `should calculate cart price with 2x1 discount`() {
        val cart = Cart(
            listOf(
                Item(Sku("A"), 2.0.toBigDecimal()),
                Item(Sku("C"), 3.0.toBigDecimal()),
                Item(Sku("C"), 3.0.toBigDecimal())
            )
        )

        val result = cart.calculateTotal(listOf(TwoForOneDiscount(Sku("C"))))

        assertThat(result).isEqualTo(5.0.toBigDecimal())
    }

    @Test
    internal fun `should not apply discounts when discounted item doesnt exist`() {
        val cart = Cart(
            listOf(
                Item(Sku("A"), 2.0.toBigDecimal()),
                Item(Sku("C"), 3.0.toBigDecimal()),
                Item(Sku("C"), 3.0.toBigDecimal())
            )
        )

        val result = cart.calculateTotal(listOf(TwoForOneDiscount(Sku("B"))))

        assertThat(result).isEqualTo(8.0.toBigDecimal())
    }
}
