package bytebandits.viruscheck

import bytebandits.interfaces.VirusChecker
import java.io.File
import java.security.MessageDigest

class VirusHashChecker {
    // This is a static method
    companion object : VirusChecker {
        override fun virusCheck(file: File): String {
            if (!file.exists() || !file.isFile) {
                throw IllegalArgumentException("File does not exist")
            }

            val searchHash = calculateMD5(file)
            val internalDatabase = File("db.txt")

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
        private fun calculateMD5(file: File): String {
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

        // byte array parsing to be implemented later
        private fun calculateMD5(byteArray: ByteArray): String {
            val md = MessageDigest.getInstance("MD5")
            val digest = md.digest(byteArray)

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
