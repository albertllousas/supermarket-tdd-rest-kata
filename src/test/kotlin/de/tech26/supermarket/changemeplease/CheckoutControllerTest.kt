package de.tech26.supermarket.changemeplease

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.springframework.http.ResponseEntity

class CheckoutControllerTest {
    
    @Test
    fun `should not calculate anything if the cart is empty of items`() {
        val result = "replace me by a real call"

        assertThat(result).isEqualTo(ResponseEntity.noContent().build<Any>())
    }
}
