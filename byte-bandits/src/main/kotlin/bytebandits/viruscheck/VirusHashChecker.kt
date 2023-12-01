package bytebandits.viruscheck

import bytebandits.hashes.Hashes.Companion.calculateMD5
import bytebandits.interfaces.VirusChecker
import bytebandits.models.SimpleFileRequest
import bytebandits.persistence.FilePersister
import java.io.File
import java.security.MessageDigest

public  class VirusHashChecker {
    //This is a static method
    companion object : VirusChecker {
        override fun virusCheck(request: SimpleFileRequest): String {

            val file = request.contents
            val searchHash = calculateMD5(file)
            val internalDatabase = File("src/main/resources/db.txt")

            val matchFound = internalDatabase.useLines { lines ->
                lines.any { line ->
                    val md5Hash = line.trim()
                    md5Hash == searchHash
                }
            }

            if (matchFound) {
                return "File is a known virus. Please delete immediately."
            }
            return "File did not match any known viruses."
        }
        override fun reportVirus(file: File): String {
            TODO("Not yet implemented")
        }
        private fun calculateMD5File(file: File): String {
            val md = MessageDigest.getInstance("MD5")
            file.inputStream().use { input ->
                val buffer = ByteArray(8192)
                var bytesRead = input.read(buffer)
                while (bytesRead > 0) {
                    md.update(buffer, 0, bytesRead)
                    bytesRead = input.read(buffer)
                }
            }
            val digest = md.digest()

            val md5Hex = StringBuilder()
            for (byte in digest) {
                md5Hex.append(String.format("%02x", byte))
            }

            return md5Hex.toString()
        }
    }
}


/*
Internal MD5 Hash Database modeled after:
################################################################
# MalwareBazaar recent malware samples (MD5 hashes)            #
# Last updated: 2023-10-18 20:30:48 UTC                        #
#                                                              #
# Terms Of Use: https://bazaar.abuse.ch/faq/#tos               #
# For questions please contact bazaar [at] abuse.ch            #
################################################################
 */
