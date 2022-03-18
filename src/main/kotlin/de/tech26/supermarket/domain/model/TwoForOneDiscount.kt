package de.tech26.supermarket.domain.model

import java.math.BigDecimal

data class TwoForOneDiscount (val sku: Sku) {

    fun calculate(items : List<Item>) : BigDecimal {
        val discountTimes: Int = items.filter { it.sku == sku }.size / 2
        val itemPrice: BigDecimal = items.find { it.sku == sku }?.price ?: 0.0.toBigDecimal()
        return itemPrice * discountTimes.toBigDecimal()
    }
}
