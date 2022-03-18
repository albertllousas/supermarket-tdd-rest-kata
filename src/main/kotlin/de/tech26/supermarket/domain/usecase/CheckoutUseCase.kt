package de.tech26.supermarket.domain.usecase

import arrow.core.Either
import arrow.core.right
import de.tech26.supermarket.domain.ItemsNotFoundError
import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class CheckoutUseCase {

    operator fun invoke(input: List<String>): Either<ItemsNotFoundError, BigDecimal> {
        return BigDecimal.ZERO.right()
    }
}
