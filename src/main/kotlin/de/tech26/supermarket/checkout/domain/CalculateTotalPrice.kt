package de.tech26.supermarket.checkout.domain

import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class CalculateTotalPrice(val productRepository: ProductRepository) {

    fun perform(skus: List<String>): BigDecimal {
        return skus.mapNotNull { productRepository.findProduct(it)?.price }
            .reduce(BigDecimal::add)
    }
}
