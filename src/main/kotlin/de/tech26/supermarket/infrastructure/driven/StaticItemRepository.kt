package de.tech26.supermarket.infrastructure.driven

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.tech26.supermarket.domain.ItemsNotFoundError
import de.tech26.supermarket.domain.model.Item
import de.tech26.supermarket.domain.model.ItemRepository
import de.tech26.supermarket.domain.model.Sku
import org.springframework.stereotype.Repository

@Repository
object StaticItemRepository : ItemRepository {

    private val itemsBySku = listOf(
        Item(Sku("A"), 2.0.toBigDecimal()),
        Item(Sku("B"), 4.0.toBigDecimal()),
    ).associateBy { it.sku }

    override fun getItemsByIds(skus: List<Sku>): Either<ItemsNotFoundError, List<Item>> =
        if (skus.all { it in itemsBySku.keys }) skus.map { itemsBySku[it]!! }.right()
        else ItemsNotFoundError(skus.filterNot { it in itemsBySku.keys }.map(Sku::id)).left()
}
