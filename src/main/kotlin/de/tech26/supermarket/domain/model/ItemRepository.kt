package de.tech26.supermarket.domain.model

import arrow.core.Either
import de.tech26.supermarket.domain.ItemsNotFoundError

interface ItemRepository {

    fun getItemsByIds(skus: List<Sku>) : Either<ItemsNotFoundError, List<Item>>

}
