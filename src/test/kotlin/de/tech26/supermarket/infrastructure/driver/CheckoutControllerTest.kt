package de.tech26.supermarket.infrastructure.driver

import com.ninjasquad.springmockk.MockkBean
import de.tech26.supermarket.domain.usecase.CheckoutUseCase
import io.mockk.every
import io.mockk.verify
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Tag("integration")
@WebMvcTest(value = [CheckoutController::class])
class CheckoutControllerTest(@Autowired private val mockMvc: MockMvc) {

    @MockkBean
    private lateinit var checkoutUseCase: CheckoutUseCase

    @Test
    fun `should not calculate anything if the cart is empty of products`() {
        val result = mockMvc.perform(
            post("/checkout").content("""{ "skus": [] }""")
                .contentType(MediaType.APPLICATION_JSON)
        )

        result.andExpect(status().isNoContent)
    }

    @Test
    fun `should checkout a cart of products`() {
        val itemsInTheCart = listOf("A", "B", "A", "B", "A", "A", "A")
        every { checkoutUseCase(itemsInTheCart) } returns 18.0

        val result = mockMvc.perform(
            post("/checkout").content("""{ "skus": ["A","B","A","B","A","A","A"] }""")
                .contentType(MediaType.APPLICATION_JSON)
        )

        verify { checkoutUseCase.invoke(itemsInTheCart) }
        result
            .andExpect(
                content().json(
                    """
                    {
                        "total": 18.00
                    }
                """.trimIndent()
                )
            )
            .andExpect(status().isCreated)
    }
}
