package bytebandits

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.testing.*
import kotlin.test.Test
import kotlin.test.assertEquals

class sample {
	@Test
	fun testGetApi() {
		withTestApplication(Application::module) {
			handleRequest(HttpMethod.Get, "storage/Get/test/test").apply {
				assertEquals(HttpStatusCode.OK, response.status())
			}
		}
	}

	@Test
	fun testTest(){
		assertEquals(true, true)
	}

}
