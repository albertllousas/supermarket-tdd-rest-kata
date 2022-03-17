package de.tech26.supermarket.domain.usecase

import arrow.core.Either
import de.tech26.supermarket.domain.ItemsNotFoundError
import org.springframework.stereotype.Service
import java.math.BigDecimal

@Service
class CheckoutUseCase {
    operator fun invoke(input: List<String>): Either<ItemsNotFoundError, BigDecimal> {
        TODO()
    }
}
