package de.tech26.supermarket.domain.usecase

import arrow.core.right
import de.tech26.supermarket.domain.model.Item
import de.tech26.supermarket.domain.model.ItemRepository
import de.tech26.supermarket.domain.model.Sku
import io.mockk.every
import io.mockk.mockk
import java.math.BigDecimal
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CheckoutUseCaseTest {

    private val itemRepository: ItemRepository = mockk<ItemRepository>()
    private val checkout: CheckoutUseCase = CheckoutUseCase(itemRepository)

    @Test
    internal fun `should not calculate anything when a cart is empty`() {
        val result = checkout(emptyList())

        assertThat(result).isEqualTo(BigDecimal.ZERO.right())
    }

    @Test
    internal fun `should checkout for not empty cart`() {
        every { itemRepository.getItemsByIds(setOf(Sku("A"), Sku("B"))) } returns listOf(
            Item(Sku("A"), 2.0.toBigDecimal()),
            Item(Sku("B"), 4.0.toBigDecimal())
        )
        val result = checkout(listOf("A", "B", "A"))

        assertThat(result).isEqualTo(BigDecimal(8.0).right())
    }
}
