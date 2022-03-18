package de.tech26.supermarket.domain.model

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class TwoForOneDiscountTest {

    @Test
    internal fun `should calculate discount given a list of items`() {
        val result =
            TwoForOneDiscount(Sku("B"))
                .calculate(listOf(
                    Item(Sku("A"), 2.0.toBigDecimal()),
                    Item(Sku("B"), 4.0.toBigDecimal()),
                    Item(Sku("B"), 4.0.toBigDecimal()))
                )

        assertThat(result).isEqualTo(4.0.toBigDecimal())
    }
}
