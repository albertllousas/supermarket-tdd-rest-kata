package de.tech26.supermarket.domain.model

import java.math.BigDecimal

data class Cart(val items: List<Item>) {

    fun calculateTotal(): BigDecimal = items.sumOf (Item::price)

    fun calculateTotal(discounts: List<TwoForOneDiscount>): BigDecimal {
        val subTotal = calculateTotal()

        val discountValue: BigDecimal = discounts.map { discount ->
            val discountTimes: Int = items.filter { it.sku == discount.sku }.size / 2
            val itemPrice: BigDecimal = items.find { it.sku == discount.sku }?.price ?: 0.0.toBigDecimal()
            val discountV: BigDecimal = itemPrice * discountTimes.toBigDecimal()

            discountV
        }.sumOf { it }

        return subTotal - discountValue
    }

}
