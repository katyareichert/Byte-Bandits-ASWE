package bytebandits.viruscheck

import bytebandits.models.SimpleFileRequest
import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import java.io.File
import java.io.FileInputStream
import kotlin.test.Test
import kotlin.test.assertFailsWith

class VirusHashCheckTests{
    @Test
    fun testVirusCheckInternalMatch(){
        val testFile = File("src/test/resources/samplefiles/knownVirus.pdf")
        val inputStream = FileInputStream(testFile)
        val fileSize = testFile.length().toInt()
        val byteArray = ByteArray(fileSize)
        inputStream.read(byteArray)
        inputStream.close()

        val testFileName = "testFile.txt"
        val testUserID = "testUserID"

        val testRequest = SimpleFileRequest(byteArray, testUserID, testFileName)
        val matchString = VirusHashChecker.virusCheck(testRequest)

        assertThat(matchString, equalTo("File is a known virus. Please delete immediately."))
    }

    @Test
    fun testVirusCheckNoMatches(){
        val testFile = File("src/test/resources/samplefiles/regularFile.pdf")
        val inputStream = FileInputStream(testFile)
        val fileSize = testFile.length().toInt()
        val byteArray = ByteArray(fileSize)
        inputStream.read(byteArray)
        inputStream.close()

        val testFileName = "testFile.txt"
        val testUserID = "testUserID"

        val testRequest = SimpleFileRequest(byteArray, testUserID, testFileName)
        val matchString = VirusHashChecker.virusCheck(testRequest)

        assertThat(matchString, equalTo("File did not match any known viruses."))
    }
}
