package bytebandits.routing

import bytebandits.models.WebSimpleFileRequest
import bytebandits.module
import com.google.gson.Gson
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class RoutingTests {

    val sampleJWT =
        "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJhdWQiOiJqd3QtYXVkaWVuY2UiLCJpc3MiOiJodHRwczovL2p3dC1wcm92aWRlci1kb21" +
                "haW4vIiwiY2xpZW50SWQiOiJzYW1wbGUiLCJleHAiOjE3MDE0NTA2MTh9.bjsON6-Rk_NRrWvw5M0Uluxcwdj9bs52wUtBsqCeRiQ"


    @Test
    fun `invalid endpoint should fail`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "storage/notreal").apply {
                assertEquals(HttpStatusCode.NotFound, response.status())
            }
        }
    }

    @Test
    fun `non auth get should fail`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "storage/Get/test").apply {
                assertEquals(HttpStatusCode.Unauthorized, response.status())
            }
        }
    }

    @Test
    fun `file not found should fail with badgateway`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "storage/Get/test") {
                addHeader("Authorization", "Bearer $sampleJWT")
            }.apply {
                assertEquals(HttpStatusCode.BadGateway, response.status())
            }
        }
    }
    
    @Test
	fun `submit should work`() {
		withTestApplication(Application::module) {
			handleRequest(HttpMethod.Post, "storage/Submit/") {

				addHeader("Authorization", "Bearer $sampleJWT")
				val obj = WebSimpleFileRequest("dGhpcyBpcyBhIHRlc3Q=", "test", "value")
				val gson = Gson()
				addHeader("Content-Type","application/json")
				setBody(gson.toJson(obj))
			}.apply {
				assertEquals(HttpStatusCode.OK, response.status())
			}

			handleRequest(HttpMethod.Get, "storage/Get/value/test") {
				addHeader("Authorization", "Bearer $sampleJWT")
			}.apply {
				assertEquals(HttpStatusCode.OK, response.status())
			}
		}
	}

    @Test
    fun `gen password`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "password/Get/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertTrue { response.content!!.length > 0 }
            }
        }
    }

    @Test
    fun `gen password 19 chars`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "password/Get/19").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                assertTrue { response.content!!.length == 19 }
            }
        }
    }


    @Test
    fun `gen passkey`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "passKey/Get/").apply {
                assertEquals(HttpStatusCode.OK, response.status())
            }
        }
    }

    @Test
    fun `gen passkey 15`() {
        withTestApplication(Application::module) {
            handleRequest(HttpMethod.Get, "passKey/Get/15").apply {
                assertEquals(HttpStatusCode.OK, response.status())
                //assertTrue { response.byteContent!!.size == 15 }
            }
        }
    }
}
