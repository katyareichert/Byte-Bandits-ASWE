package bytebandits.encryption

import kotlin.test.*

class EncryptionTests{
  @Test
  fun testEncryptDecryptFileWithValidKeyAndScheme(){
    val testFileContent = "This is a test file".toByteArray()
    val testValid256Key = "12345678901234567890123456789012".toByteArray()

    val encryptedFile = Encryption.FileEncrypt(testFileContent, testValid256Key, "AES")
    val decryptedFile = Encryption.FileDecrypt(encryptedFile, testValid256Key, "AES")

    assertTrue(testFileContent.contentEquals(decryptedFile))
  }

  @Test
  fun testDecryptFileWithWrongKey(){
    val testFileContent = "This is a test file".toByteArray()
    val testValid256Key = "12345678901234567890123456789012".toByteArray()
    val testValid256KeyWrong = "12345678901234567890123456789013".toByteArray()

    val encryptedFile = Encryption.FileEncrypt(testFileContent, testValid256Key, "AES")

    assertFailsWith(IllegalArgumentException::class){
      Encryption.FileDecrypt(encryptedFile, testValid256KeyWrong, "AES")
    }
  }

  @Test
  fun testDecryptEncryptFileWithInvalidScheme(){
    val testFileContent = "This is a test file".toByteArray()
    val testValid256Key = "12345678901234567890123456789012".toByteArray()

    assertFailsWith(IllegalArgumentException::class){
      Encryption.FileEncrypt(testFileContent, testValid256Key, "WRONG")
    }

    assertFailsWith(IllegalArgumentException::class){
      Encryption.FileDecrypt(testFileContent, testValid256Key, "WRONG")
    }
  }

  @Test
  fun testEncryptDecryptFileWithInvalidKey(){
    val testFileContent = "This is a test file".toByteArray()
    val testInvalidKeyShort = "1234567890123456789012345678901".toByteArray()
    val testInvalidKeyLong = "123456789012345678901234567890123".toByteArray()

    assertFailsWith(IllegalArgumentException::class){
      Encryption.FileEncrypt(testFileContent, testInvalidKeyShort, "AES")
    }
    assertFailsWith(IllegalArgumentException::class){
      Encryption.FileEncrypt(testFileContent, testInvalidKeyLong, "AES")
    }
    assertFailsWith(IllegalArgumentException::class){
      Encryption.FileDecrypt(testFileContent, testInvalidKeyShort, "AES")
    }
    assertFailsWith(IllegalArgumentException::class){
      Encryption.FileDecrypt(testFileContent, testInvalidKeyLong, "AES")
    }
  }
}