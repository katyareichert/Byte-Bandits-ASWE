package bytebandits.viruscheck

import bytebandits.interfaces.VirusChecker
import java.security.MessageDigest
import java.io.File

public  class VirusHashChecker {
    //This is a static method
    companion object : VirusChecker {
        override fun VirusCheck(file: File): String {
            TODO("Not yet implemented")
        }

        override fun ReportVirus(file: File): String {
            TODO("Not yet implemented")
        }
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