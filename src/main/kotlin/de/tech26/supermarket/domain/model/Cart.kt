package de.tech26.supermarket.domain.model

import java.math.BigDecimal

data class Cart(val items: List<Item>) {

    fun calculateTotal(): BigDecimal = items.sumOf (Item::price)

}
