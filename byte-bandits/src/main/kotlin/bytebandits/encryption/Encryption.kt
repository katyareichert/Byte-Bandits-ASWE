package bytebandits.encryption

import bytebandits.interfaces.Encrypter
import javax.crypto.Cipher
import javax.crypto.SecretKey
import javax.crypto.spec.SecretKeySpec


public class Encryption {

  companion object : Encrypter {
    override fun fileEncrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray {
      val secretKey: SecretKey

      require(isValidScheme(scheme)) { "Invalid encryption scheme: $scheme" }

      try {
        secretKey = validateAndPrepareKey(key)
      } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Invalid key: ${e.message}")
      }

      val cipher = Cipher.getInstance(scheme)
      cipher.init(Cipher.ENCRYPT_MODE, secretKey)

      return cipher.doFinal(contents)
    }


    override fun fileEncrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray {
      // Derive key from password
      // Call FileEncrypt(contents, key, scheme)
      return "Hey, i'm a file".toByteArray()
    }
    override fun fileDecrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray {
      val secretKey: SecretKey

      require(isValidScheme(scheme)) { "Invalid encryption scheme: $scheme" }

      try {
        secretKey = validateAndPrepareKey(key)
      } catch (e: IllegalArgumentException) {
        throw IllegalArgumentException("Invalid key: ${e.message}")
      }

      val cipher = Cipher.getInstance(scheme)
      cipher.init(Cipher.DECRYPT_MODE, secretKey)
      try {
        return cipher.doFinal(contents)
      } catch (e: Exception) {
        throw IllegalArgumentException("Invalid key: ${e.message}")
      }
    }

    override fun fileDecrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray {
      return "Hey, i'm a file".toByteArray()
    }

    private fun isValidScheme(scheme: String?): Boolean {
      // Implement your scheme validation logic here
      // Return true if the scheme is valid, false otherwise
      return scheme == "AES"
    }
    private fun validateAndPrepareKey(key: ByteArray?) : SecretKeySpec {
      if (key == null) {
        throw IllegalArgumentException("Key must not be null")
      }
      if (key.size != 32) {
        throw IllegalArgumentException("Key must be 32 bytes in length")
      }
      return SecretKeySpec(key, "AES")
    }
  }
}
