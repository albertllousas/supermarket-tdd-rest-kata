package de.tech26.supermarket.domain.usecase

import arrow.core.Either
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

        val skus = input
            .map { Sku(it) }
            .toSet()

        val items = itemRepository.getItemsByIds(skus)

        input.foldRight(BigDecimal.ZERO.right()) { value, acc -> items.find { it.sku == value }?.let { acc.map {  } }}


    }
}
