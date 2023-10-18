package bytebandits.encryption

import bytebandits.interfaces.Encrypter

public class encryption  {
  companion object : Encrypter {
    override fun SimpleFileEncrypt(contents:String, key: String?): Boolean {
      return true
    }

    override fun SimpleFileDecrypt(contents: String, key: String?, scheme:String): String {
      return "Hey, i'm a file"
    }

    override fun SimpleFileDecrypt(contents: ByteArray, key: String?, scheme:String): ByteArray {
      return "Hey, i'm a file".toByteArray()
    }
  }
}