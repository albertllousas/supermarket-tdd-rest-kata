package de.tech26.supermarket.domain

sealed class Error {}

data class ItemsNotFoundError(
    val items: List<String>
) : Error()
