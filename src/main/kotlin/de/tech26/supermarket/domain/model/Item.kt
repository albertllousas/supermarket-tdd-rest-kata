package de.tech26.supermarket.domain.model

import java.math.BigDecimal

data class Item (val sku: Sku, val price: BigDecimal)