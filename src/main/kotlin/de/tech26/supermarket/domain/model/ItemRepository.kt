package de.tech26.supermarket.domain.model

interface ItemRepository {

    fun getItemsByIds(skus: Set<Sku>) : List<Item>

}