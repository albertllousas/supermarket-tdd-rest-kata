package de.tech26.supermarket.checkout

import java.math.BigDecimal
import org.springframework.stereotype.Service

@Service
class CalculateTotalPrice {
    fun perform(skus: List<StockKeepingUnit>): BigDecimal = TODO()
}
