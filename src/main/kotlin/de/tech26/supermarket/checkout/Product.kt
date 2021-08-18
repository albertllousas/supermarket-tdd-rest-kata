package de.tech26.supermarket.checkout

import java.math.BigDecimal

data class Product(val sku: String, val price: BigDecimal, val description: String)
