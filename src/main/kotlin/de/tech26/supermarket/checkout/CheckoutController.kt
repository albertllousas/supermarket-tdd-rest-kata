package de.tech26.supermarket.checkout

import java.math.BigDecimal
import org.springframework.http.HttpStatus.CREATED
import org.springframework.http.HttpStatus.NO_CONTENT
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController(
    private val calculateTotalPrice: CalculateTotalPrice
) {
    @PostMapping("/checkout")
    fun checkout(@RequestBody checkoutRequest: CheckoutRequest): ResponseEntity<CheckoutResponse> {
        return if (checkoutRequest.skus.isEmpty()) {
            ResponseEntity(NO_CONTENT)
        } else {
            val totalPrice = calculateTotalPrice.perform(checkoutRequest.skus)
            ResponseEntity.status(CREATED).body(CheckoutResponse(totalPrice.setScale(2)))
        }
    }
}