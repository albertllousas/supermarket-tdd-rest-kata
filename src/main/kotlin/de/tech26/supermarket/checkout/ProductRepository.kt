package de.tech26.supermarket.checkout

interface ProductRepository {
    fun findProduct(sku: String): Product?
}
