package bytebandits.encryption

import kotlin.test.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class EncKeyGenerationTest {
    @Test
    fun testGenerateKeyWithDifferentSeedsAndAppSecretTrue() {
        val key1 = EncKeyGeneration.generateKey("seed1", true, "salt")
        val key2 = EncKeyGeneration.generateKey("seed2", true, "salt")
        assertFalse(key1 contentEquals key2)
    }

    @Test
    fun testGenerateKeyWithDifferentSeedsAndAppSecretFalse() {
        val key1 = EncKeyGeneration.generateKey("seed1", false, "salt")
        val key2 = EncKeyGeneration.generateKey("seed2", false, "salt")
        assertFalse(key1 contentEquals key2)
    }

    @Test
    fun testGenerateKeyWithDifferentSalts() {
        val key1 = EncKeyGeneration.generateKey("seed", false, "salt1")
        val key2 = EncKeyGeneration.generateKey("seed", false, "salt2")
        assertFalse(key1 contentEquals key2)
    }

    @Test
    fun testGenerateKeyWithSameSeedSaltAndAppSecretTrue() {
        val key1 = EncKeyGeneration.generateKey("seed", true, "salt")
        val key2 = EncKeyGeneration.generateKey("seed", true, "salt")
        assertTrue(key1 contentEquals key2)
    }

    @Test
    fun testGenerateKeyWithSameSeedSaltAndAppSecretFalse() {
        val key1 = EncKeyGeneration.generateKey("seed", false, "salt")
        val key2 = EncKeyGeneration.generateKey("seed", false, "salt")
        assertTrue(key1 contentEquals key2)
    }
}