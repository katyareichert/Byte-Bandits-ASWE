package bytebandits.hashes

import bytebandits.models.SimpleFileRequest
import kotlin.test.Test
import kotlin.test.assertEquals

class HashesTest {
    @Test
    fun testHashCheck() {
        val request = SimpleFileRequest("validBase64Contents".toByteArray(), "validUserID", "validFileName")
        val hash = Hashes.hashCheck(request)
        assertEquals("01d5e622b1eb13b30f5dacdb25df4e78", hash)
    }

    @Test
    fun testHashStore() {
        val request = SimpleFileRequest("validBase64Contents".toByteArray(), "validUserID", "validFileName")
        val hash = Hashes.hashStore(request, "validClientID")
        assertEquals("01d5e622b1eb13b30f5dacdb25df4e78", hash)
    }

    @Test
    fun testHashRetrieve() {
        val fileContents = Hashes.hashRetrieve("01d5e622b1eb13b30f5dacdb25df4e78", "validClientID", "validUserID")
        assertEquals("validBase64Contents", String(fileContents))
    }

    @Test
    fun testCalculateMD5() {
        val byteArray = "validBase64Contents".toByteArray()
        val md5Hash = Hashes.calculateMD5(byteArray)
        assertEquals("01d5e622b1eb13b30f5dacdb25df4e78", md5Hash)
    }
}