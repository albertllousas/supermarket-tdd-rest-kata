package de.tech26.supermarket.changemeplease

import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status

@Tag("integration")
@WebMvcTest(value = [ChangeMeController::class])
class ChangeMeControllerTest(@Autowired private val mockMvc: MockMvc) {

//    @MockkBean(relaxed = true)
//    private lateinit var myDependency: MyDependency

    @Test
    fun `should not calculate anything if the cart is empty of products`() {
        val result = mockMvc.perform(
            post("/checkout").content("""{ "skus": [] }""")
        )

        result.andExpect(status().isNoContent)
    }

    @Test
    fun `should checkout a cart of products`() {
        val result = mockMvc.perform(
            post("/checkout").content("""{ "skus": ["A","B","A","B","A","A","A"] }""")
        )

        result
            .andExpect(content().json(
                """
                    {
                        "total": 18.00
                    }
                """.trimIndent()
            ))
            .andExpect(status().isCreated)
    }
}
