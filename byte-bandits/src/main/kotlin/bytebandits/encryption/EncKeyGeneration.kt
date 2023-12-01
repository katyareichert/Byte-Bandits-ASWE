package bytebandits.encryption

import bytebandits.interfaces.KeyGenerator
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec

public class EncKeyGeneration {
    companion object : KeyGenerator {
        override fun generateKey(seed: String, appSecret: Boolean, salt: String?): ByteArray {
            var modifiedSeed = seed
            if (appSecret) {
                modifiedSeed += "secret"
            }

            val modifiedSalt = salt ?: "salt"

            val iterationCount = 10000
            val keyLength = 256

            // Create a PBEKeySpec with the seed.
            val keySpec = PBEKeySpec(modifiedSeed.toCharArray(), modifiedSalt.toByteArray(), iterationCount, keyLength)

            // Create a SecretKeyFactory for PBKDF2 with HMAC-SHA256
            val keyFactory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA256")

            // Generate and return the key
            val keyBytes = keyFactory.generateSecret(keySpec).encoded
            return keyBytes
        }
    }
}