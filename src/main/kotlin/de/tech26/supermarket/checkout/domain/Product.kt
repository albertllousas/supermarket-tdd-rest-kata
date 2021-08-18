package de.tech26.supermarket.checkout.domain

import java.math.BigDecimal

data class Product(val sku: String, val price: BigDecimal, val description: String)
