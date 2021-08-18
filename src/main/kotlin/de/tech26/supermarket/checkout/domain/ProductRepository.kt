package de.tech26.supermarket.checkout.domain

interface ProductRepository {
    fun findProduct(sku: String): Product?
}
