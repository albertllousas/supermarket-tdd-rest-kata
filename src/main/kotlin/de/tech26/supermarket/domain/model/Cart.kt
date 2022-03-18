package de.tech26.supermarket.domain.model

import java.math.BigDecimal

data class Cart(val items: List<Item>) {

    fun calculateTotal(): BigDecimal = items.sumOf (Item::price)

    fun calculateTotal(discounts: List<TwoForOneDiscount>): BigDecimal {
        val subTotal = calculateTotal()
        val discountValue: BigDecimal = discounts.map { it.calculate(items) }.sumOf { it }
        return subTotal - discountValue
    }

}
