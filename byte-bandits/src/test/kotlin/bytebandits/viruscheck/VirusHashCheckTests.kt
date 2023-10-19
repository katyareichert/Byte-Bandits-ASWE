package bytebandits.viruscheck

import org.hamcrest.CoreMatchers.equalTo
import org.hamcrest.MatcherAssert.assertThat
import kotlin.test.*
import java.io.File

class VirusHashCheckTests{
    @Test
    fun testVirusCheckWithInvalidFile(){
        val testFile = File("fileName")
        val exception = assertFailsWith<IllegalArgumentException>(
            message = "No exception found",
            block = {
                VirusHashChecker.virusCheck(testFile)
            }
        )
        assertThat(exception.message, equalTo("File does not exist"))
    }

    @Test
    fun testVirusCheckInternalMatch(){
        val testFile = File("../../../../../resources/samplefiles/knownVirus.pdf")
        val matchString = VirusHashChecker.virusCheck(testFile)

        assertThat(matchString, equalTo("File is a known virus. Please delete immediately."))
    }

    @Test
    fun testVirusCheckNoMatches(){
        val testFile = File("../../../../../resources/samplefiles/regularFile.pdf")
        val matchString = VirusHashChecker.virusCheck(testFile)

        assertThat(matchString, equalTo("File did not match any known viruses."))
    }
}