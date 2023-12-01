package bytebandits.persistence

import bytebandits.models.SimpleFileRequest


import kotlin.test.Test
import kotlin.test.assertFailsWith

class PersistenceTests {
    @Test
    fun testSimpleFilePersistFull() {
        val testFileContent = "This is a test file".toByteArray()
        val testFileName = "testFile.txt"
        val testEndpointDirectory = "testEndpoint"
        val testClientID = "testClientID"
        val testUserID = "testUserID"
        val testOverwrite = true

        val testSimpleFilePersist =
            FilePersister.simpleFilePersist(testFileContent, testUserID, testFileName, testEndpointDirectory, testClientID, testOverwrite)

        assert(testSimpleFilePersist)

        val testSimpleFileRetrieve =
            FilePersister.simpleFileRetrieve(testFileName, testEndpointDirectory, testClientID, testUserID)

        assert(testSimpleFileRetrieve.contentEquals(testFileContent))

        val testSimpleFileDelete =
            FilePersister.simpleFileDelete(testFileName, testEndpointDirectory, testClientID, testUserID)

        assert(testSimpleFileDelete)
    }

    @Test
    fun testSimpleFilePersistNoUserID() {
        val testFileContent = "This is a test file".toByteArray()
        val testFileName = "testFile.txt"
        val testEndpointDirectory = "testEndpoint"
        val testClientID = "testClientID"
        val testUserID = null
        val testOverwrite = true


        val testSimpleFilePersist =
            FilePersister.simpleFilePersist(testFileContent, testUserID, testFileName, testEndpointDirectory, testClientID, testOverwrite)

        assert(testSimpleFilePersist)

        val testSimpleFileRetrieve =
            FilePersister.simpleFileRetrieve(testFileName, testEndpointDirectory, testClientID, testUserID)

        assert(testSimpleFileRetrieve.contentEquals(testFileContent))

        val testSimpleFileDelete =
            FilePersister.simpleFileDelete(testFileName, testEndpointDirectory, testClientID, testUserID)

        assert(testSimpleFileDelete)
    }

    @Test
    fun testSimpleFilePersistNoClientIDNoUserID() {
        val testFileContent = "This is a test file".toByteArray()
        val testFileName = "testFile.txt"
        val testEndpointDirectory = "testEndpoint"
        val testClientID = null
        val testUserID = null
        val testOverwrite = true

        val testSimpleFilePersist =
            FilePersister.simpleFilePersist(testFileContent, testUserID, testFileName, testEndpointDirectory, testClientID, testOverwrite)

        assert(testSimpleFilePersist)

        val testSimpleFileRetrieve =
            FilePersister.simpleFileRetrieve(testFileName, testEndpointDirectory, testClientID, testUserID)

        assert(testSimpleFileRetrieve.contentEquals(testFileContent))

        val testSimpleFileDelete =
            FilePersister.simpleFileDelete(testFileName, testEndpointDirectory, testClientID, testUserID)

        assert(testSimpleFileDelete)
    }

    @Test
    fun testSimpleFilePersistNoClientID() {
        val testFileContent = "This is a test file".toByteArray()
        val testFileName = "testFile.txt"
        val testEndpointDirectory = "testEndpoint"
        val testClientID = null
        val testUserID = "testUserID"
        val testOverwrite = true

        assertFailsWith(IllegalArgumentException::class) {
            FilePersister.simpleFilePersist(testFileContent, testUserID, testFileName, testEndpointDirectory, testClientID, testOverwrite)
        }

        assertFailsWith(IllegalArgumentException::class) {
            FilePersister.simpleFileRetrieve(testFileName, testEndpointDirectory, testClientID, testUserID)
        }

        assertFailsWith(IllegalArgumentException::class) {
            FilePersister.simpleFileDelete(testFileName, testEndpointDirectory, testClientID, testUserID)
        }
    }

    @Test
    fun testSimpleFilePersistNoOverwrite() {
        val testFileContent = "This is a test file".toByteArray()
        val testFileContent2 = "This is a test file 2".toByteArray()
        val testFileName = "testFile.txt"
        val testEndpointDirectory = "testEndpoint"
        val testClientID = "testClientID"
        val testUserID = "testUserID"
        val testOverwrite = false


        val testSimpleFilePersist =
            FilePersister.simpleFilePersist(testFileContent, testUserID, testFileName, testEndpointDirectory, testClientID, testOverwrite)

        assert(testSimpleFilePersist)

        assertFailsWith<IllegalArgumentException> {
            FilePersister.simpleFilePersist(testFileContent2, testUserID, testFileName, testEndpointDirectory, testClientID, testOverwrite)
        }

        val testSimpleFileRetrieve =
            FilePersister.simpleFileRetrieve(testFileName, testEndpointDirectory, testClientID, testUserID)

        assert(testSimpleFileRetrieve.contentEquals(testFileContent))

        val testSimpleFileDelete =
            FilePersister.simpleFileDelete(testFileName, testEndpointDirectory, testClientID, testUserID)

        assert(testSimpleFileDelete)
    }
}
