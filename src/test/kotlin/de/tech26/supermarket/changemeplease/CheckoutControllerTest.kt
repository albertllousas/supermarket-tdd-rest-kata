package de.tech26.supermarket.changemeplease

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.ResponseEntity
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

class CheckoutControllerTest {
    
    @Test
    fun `should not calculate anything if the cart is empty of items`() {
        val result = "replace me by a real call"

        assertThat(result).isEqualTo(ResponseEntity.noContent().build<Any>())
    }
}

// Some people prefer integration over unit in http controller tests, matter of taste ;-)
// Choose the one you prefer: unit or integration web test and remove the other

@Tag("integration")
@WebMvcTest(value = [/*my controller class*/])
class CheckoutControllerWebTest(@Autowired private val mockMvc: MockMvc) {

    @Test
    fun `should not calculate anything if the cart is empty of items`() {
        val result = mockMvc.perform(post("/supermarket/checkout"))

        result.andExpect(status().isNoContent)
    }
}
