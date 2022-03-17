package de.tech26.supermarket.infrastructure.driver

import arrow.core.Either
import arrow.core.computations.result
import arrow.core.left
import arrow.core.right
import com.ninjasquad.springmockk.MockkBean
import de.tech26.supermarket.domain.ItemNotFoundError
import de.tech26.supermarket.domain.ItemsNotFoundError
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
import java.math.BigDecimal

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
        every { checkoutUseCase(itemsInTheCart) } returns BigDecimal(18.0).right()

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

    @Test
    internal fun `should return 404 Not Found when item wasn't found`() {
        val itemsInTheCart = listOf("C")
        every { checkoutUseCase(itemsInTheCart) } returns ItemsNotFoundError(listOf("C")).left()

        val result = mockMvc.perform(
            post("/checkout").content("""{ "skus": ["A","B","A","B","A","A","A"] }""")
                .contentType(MediaType.APPLICATION_JSON)
        )

        verify { checkoutUseCase.invoke(itemsInTheCart) }
        result.andExpect(status().isNotFound)
    }
}
