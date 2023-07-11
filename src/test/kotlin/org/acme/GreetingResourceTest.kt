package org.acme

import io.quarkus.test.junit.QuarkusTest
import io.restassured.RestAssured.given
import org.hamcrest.CoreMatchers.`is`
import org.junit.jupiter.api.Test

@QuarkusTest
class GreetingResourceTest {

    @Test
    fun testHelloEndpoint() {
        given()
          .`when`().get("/hello")
          .then()
             .statusCode(200)
             .body(`is`("Test Point"))
    }

    @Test
    fun testMovieEndpoint() {
        given()
            .`when`().get("/api/movies")
            .then()
            .statusCode(200)
    }

    @Test
    fun testEndpoint() {
        given()
            .`when`().get("/api/users/test")
            .then()
            .statusCode(200)
    }

    @Test
    fun test2Endpoint() {
        given()
            .`when`().get("/api/users/test2")
            .then()
            .statusCode(401)
    }


}