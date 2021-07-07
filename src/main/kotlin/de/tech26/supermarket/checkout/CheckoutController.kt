package de.tech26.supermarket.checkout

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class CheckoutController {

    @PostMapping("/checkout")
    fun checkout(): ResponseEntity<Any> = ResponseEntity(HttpStatus.NO_CONTENT)

}