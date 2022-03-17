package de.tech26.supermarket.infrastructure.driver

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController {

    @PostMapping("/checkout")
    fun checkout(@RequestBody request: HttpCheckoutRequest): ResponseEntity<HttpCheckoutResponse> {
        if(request.skus.isEmpty()) {
            return ResponseEntity(HttpStatus.NO_CONTENT)
        }
        return ResponseEntity(HttpCheckoutResponse(18.0), HttpStatus.CREATED)
    }

}

data class HttpCheckoutRequest (val skus: List<String>)

data class HttpCheckoutResponse(val total: Double)
