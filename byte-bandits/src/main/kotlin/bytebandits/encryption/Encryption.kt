package bytebandits.encryption

import bytebandits.interfaces.Encrypter
import javax.crypto.Cipher
import javax.crypto.spec.SecretKeySpec


public class Encryption  {

  companion object : Encrypter {
    override fun FileEncrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray {

      if (!isValidScheme(scheme)) {
        throw IllegalArgumentException("Invalid encryption scheme: $scheme")
      }

      val secretKey = validateAndPrepareKey(key)
      val cipher = Cipher.getInstance(scheme)
      cipher.init(Cipher.ENCRYPT_MODE, secretKey)

      return cipher.doFinal(contents)
    }


    override fun FileEncrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray {
      // Derive key from password
      // Call FileEncrypt(contents, key, scheme)
      return "Hey, i'm a file".toByteArray()
    }

    override fun FileDecrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray {
      if (!isValidScheme(scheme)) {
        throw IllegalArgumentException("Invalid encryption scheme: $scheme")
      }

      val secretKey = validateAndPrepareKey(key)
      val cipher = Cipher.getInstance(scheme)
      cipher.init(Cipher.DECRYPT_MODE, secretKey)

      return cipher.doFinal(contents)
    }

    override fun FileDecrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray {
      return "Hey, i'm a file".toByteArray()
    }

    fun isValidScheme(scheme: String?): Boolean {
      // Implement your scheme validation logic here
      // Return true if the scheme is valid, false otherwise
      return scheme == "AES" || scheme == "AES192" || scheme == "AES256"
    }
    fun validateAndPrepareKey(key: ByteArray?) : SecretKeySpec {
      if (key == null) {
        throw IllegalArgumentException("Key must not be null")
      }
      if (key.size != 16 && key.size != 24 && key.size != 32) {
        throw IllegalArgumentException("Key must be 16, 24, or 32 bytes in length")
      }
      return SecretKeySpec(key, "AES")
    }
  }
}

//object KeyDeriver {
//  fun deriveKeyFromPassword(password: String, keyLength: Int): ByteArray {
//    // Implement key derivation logic here
//  }
//}