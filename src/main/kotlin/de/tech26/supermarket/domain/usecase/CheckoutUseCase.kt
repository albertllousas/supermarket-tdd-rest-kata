package de.tech26.supermarket.domain.usecase

import arrow.core.Either
import de.tech26.supermarket.domain.ItemsNotFoundError
import de.tech26.supermarket.domain.model.ItemRepository
import de.tech26.supermarket.domain.model.Sku
import java.math.BigDecimal
import java.math.BigDecimal.ZERO
import org.springframework.stereotype.Service

@Service
class CheckoutUseCase(private val itemRepository: ItemRepository) {

    operator fun invoke(input: List<String>): Either<ItemsNotFoundError, BigDecimal> =
        itemRepository.getItemsByIds(input.map(::Sku))
            .map { it.foldRight(ZERO) { item, total -> total + item.price } }
}
