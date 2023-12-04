import bytebandits.models.WebSimpleFileRequest
import bytebandits.models.SimpleFileRequest
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotEquals

class WebSimpleFileRequestTest {

  @Test
  fun equalsTest() {
    val webSimpleFileRequest1 = WebSimpleFileRequest("contents", "userID", "fileName")
    val webSimpleFileRequest2 = WebSimpleFileRequest("contents", "userID", "fileName")

    assertEquals(webSimpleFileRequest1, webSimpleFileRequest2)
  }

  @Test
  fun notEqualsTest() {
    val webSimpleFileRequest1 = WebSimpleFileRequest("contents", "userID", "fileName")
    val webSimpleFileRequest2 = WebSimpleFileRequest("differentContents", "differentUserID", "differentFileName")

    assertNotEquals(webSimpleFileRequest1, webSimpleFileRequest2)
  }
}

class SimpleFileRequestTest {

  @Test
  fun equalsTest() {
    val simpleFileRequest1 = SimpleFileRequest("contents".toByteArray(), "userID", "fileName")
    val simpleFileRequest2 = SimpleFileRequest("contents".toByteArray(), "userID", "fileName")

    assertEquals(simpleFileRequest1, simpleFileRequest2)
  }

  @Test
  fun notEqualsTest() {
    val simpleFileRequest1 = SimpleFileRequest("contents".toByteArray(), "userID", "fileName")
    val simpleFileRequest2 = SimpleFileRequest("differentContents".toByteArray(), "differentUserID", "differentFileName")

    assertNotEquals(simpleFileRequest1, simpleFileRequest2)
  }
}