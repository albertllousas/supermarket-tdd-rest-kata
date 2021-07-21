package de.tech26.supermarket.checkout

import com.ninjasquad.springmockk.MockkBean
import de.tech26.supermarket.checkout.StockKeepingUnit.A
import de.tech26.supermarket.checkout.StockKeepingUnit.B
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.math.BigDecimal

@Tag("integration")
@WebMvcTest(value = [CheckoutController::class])
class CheckoutControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var calculateTotalPrice: CalculateTotalPrice

    @Test
    fun `should not calculate anything if the cart is empty of products`() {
        val result = mockMvc.perform(
            post("/checkout")
                .contentType(APPLICATION_JSON)
                .content("""{ "skus": [] }""")
        )

        result.andExpect(status().isNoContent)
        verify(exactly = 0) { calculateTotalPrice.perform(any()) }
    }

    @Test
    fun `should return the calculated total price for the requested skus`() {
        every { calculateTotalPrice.perform(any<List<StockKeepingUnit>>()) } returns BigDecimal.valueOf(18)
        val result = mockMvc.perform(
            post("/checkout")
                .contentType(APPLICATION_JSON)
                .content("""{ "skus": ["A","B","A","B","A","A","A"] }""")
        )
        result
            .andExpect(status().isCreated)
            .andExpect(content().string("""{"total":18.00}"""))

        verify { calculateTotalPrice.perform(listOf(A, B, A, B, A, A, A)) }
    }

}
