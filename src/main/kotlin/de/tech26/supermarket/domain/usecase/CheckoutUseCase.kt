package de.tech26.supermarket.domain.usecase

import arrow.core.Either
import arrow.core.left
import arrow.core.right
import de.tech26.supermarket.domain.ItemsNotFoundError
import de.tech26.supermarket.domain.model.ItemRepository
import de.tech26.supermarket.domain.model.Sku
import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class CheckoutUseCase(private val itemRepository: ItemRepository) {
    private val zero: Either<ItemsNotFoundError, BigDecimal> = BigDecimal.ZERO.right()

    operator fun invoke(input: List<String>): Either<ItemsNotFoundError, BigDecimal> {
        val items = itemRepository.getItemsByIds(input.map(::Sku).toSet())

        return input.foldRight(zero) { value, acc ->
            items.find { it.sku == Sku(value) }
                ?.let { acc.map { total -> it.price + total } }
                ?: itemsNotFoundError(acc, value)
        }
    }

    private fun itemsNotFoundError(
        either: Either<ItemsNotFoundError, BigDecimal>,
        value: String
    ) = if (either.isLeft())
        either.mapLeft { ItemsNotFoundError(listOf(value) + it.items) }
    else
        ItemsNotFoundError(listOf(value)).left()
}
