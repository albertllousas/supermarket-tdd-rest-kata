package de.tech26.supermarket.infrastructure.driven

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import arrow.core.sequenceEither
import de.tech26.supermarket.domain.ItemsNotFoundError
import de.tech26.supermarket.domain.model.Item
import de.tech26.supermarket.domain.model.ItemRepository
import de.tech26.supermarket.domain.model.Sku

object StaticItemRepository : ItemRepository {

    private val itemsBySku = mapOf(
        Sku("A") to Item(Sku("A"), 2.0.toBigDecimal()),
        Sku("B") to Item(Sku("B"), 4.0.toBigDecimal()),
    )

    override fun getItemsByIds(skus: List<Sku>): Either<ItemsNotFoundError, List<Item>> {
        return skus
            .map { itemsBySku[it]?.right() ?: ItemsNotFoundError(listOf(it.id)).left() }
            .sequenceEither()
    }
}
