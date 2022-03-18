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
    operator fun invoke(input: List<String>): Either<ItemsNotFoundError, BigDecimal> {
        if (input.isEmpty()) BigDecimal.ZERO.right()

        val items = itemRepository.getItemsByIds(input.map(::Sku).toSet())
        val zero: Either<ItemsNotFoundError, BigDecimal> = BigDecimal.ZERO.right()
        return input.foldRight(zero) { value, acc ->
            items.find { it.sku == Sku(value) }?.let {
                acc.map { x -> it.price + x }
            } ?: ItemsNotFoundError(listOf(value)).left()
        }
    }
}
