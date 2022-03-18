package de.tech26.supermarket.domain.usecase

import arrow.core.Either
import de.tech26.supermarket.domain.ItemsNotFoundError
import de.tech26.supermarket.domain.model.Cart
import de.tech26.supermarket.domain.model.ItemRepository
import de.tech26.supermarket.domain.model.Sku
import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class CheckoutUseCase(private val itemRepository: ItemRepository) {

    operator fun invoke(input: List<String>): Either<ItemsNotFoundError, BigDecimal> =
        itemRepository.getItemsByIds(input.map(::Sku))
            .map(::Cart)
            .map(Cart::calculateTotal)
}
