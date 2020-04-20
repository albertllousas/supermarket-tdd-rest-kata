package de.tech26.supermarket.acceptance

import io.restassured.RestAssured
import net.javacrumbs.jsonunit.assertj.JsonAssertions.assertThatJson
import org.junit.jupiter.api.Tag
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort

@Tag("acceptance")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CalculateCartTotalAcceptanceTest {

    @LocalServerPort
    val springBootPort: Int = 0

    @Test
    fun `should calculate the total of the cart given a list of skus`() {
        RestAssured
            .given()
            .body(
                """
					{ 
						"skus": ["A","B","A","B","A","A","A"] 
					}
				"""
            )
            .`when`()
            .port(springBootPort)
            .post("/supermarket/checkout")
            .then()
            .assertThat()
            .statusCode(201)
            .extract()
            .response()
            .also {
                assertThatJson(it.body.asString()).isEqualTo(
                    """
					{
					  "total": 24.00
					}
					"""
                )
            }
    }
}
