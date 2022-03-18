package de.tech26.supermarket.infrastructure.driver

import arrow.core.right
import de.tech26.supermarket.domain.model.Item
import de.tech26.supermarket.domain.model.Sku
import de.tech26.supermarket.infrastructure.driven.StaticItemRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class StaticItemRepositoryTest {
    @Test
    internal fun `should find items`() {
        val result = StaticItemRepository.getItemsByIds(listOf(Sku("A")))

        assertThat(result).isEqualTo(listOf(Item(Sku("A"), 2.0.toBigDecimal())).right())
    }
}
