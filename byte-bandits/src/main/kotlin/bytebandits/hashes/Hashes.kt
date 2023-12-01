package bytebandits.hashes

import bytebandits.interfaces.HashCheckNStore
import bytebandits.models.SimpleFileRequest
import bytebandits.persistence.FilePersister.Companion.simpleFilePersist
import bytebandits.persistence.FilePersister.Companion.simpleFileRetrieve
import java.security.MessageDigest

public class Hashes {
    companion object : HashCheckNStore {
        override fun hashCheck(request: SimpleFileRequest): String {
            val fileContents = request.contents
            val hash = calculateMD5(fileContents)
            return hash
        }

        override fun hashStore(request: SimpleFileRequest, clientID: String): String {
            val fileContents = request.contents
            val userID = request.userID
            val hash = calculateMD5(fileContents)
            simpleFilePersist(fileContents, userID, hash, "hash", clientID, true)
            return hash
        }

        override fun hashRetrieve(hash: String, clientID: String, userID: String?): ByteArray {
            val fileContents = simpleFileRetrieve(hash, "hash", clientID, userID)
            return fileContents
        }


        fun calculateMD5(byteArray: ByteArray): String {
            val md = MessageDigest.getInstance("MD5")
            md.update(byteArray)
            val digest = md.digest()

            val md5Hex = StringBuilder()
            for (byte in digest) {
                md5Hex.append(String.format("%02x", byte))
            }

            return md5Hex.toString()
        }
    }
}