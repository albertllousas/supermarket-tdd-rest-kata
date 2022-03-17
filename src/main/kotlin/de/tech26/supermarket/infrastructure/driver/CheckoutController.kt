package de.tech26.supermarket.infrastructure.driver

import de.tech26.supermarket.domain.usecase.CheckoutUseCase
import org.springframework.http.HttpStatus.*
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController(
    private val checkoutUseCase: CheckoutUseCase
) {

    @PostMapping("/checkout")
    fun checkout(@RequestBody request: HttpCheckoutRequest): ResponseEntity<HttpCheckoutResponse> =
        if (request.skus.isEmpty()) ResponseEntity(NO_CONTENT)
        else checkoutUseCase(request.skus)
            .fold(
                ifLeft = { ResponseEntity<HttpCheckoutResponse>(NOT_FOUND) },
                ifRight = { ResponseEntity(HttpCheckoutResponse(it.toDouble()), CREATED) })
}

data class HttpCheckoutRequest(val skus: List<String>)

data class HttpCheckoutResponse(val total: Double)
