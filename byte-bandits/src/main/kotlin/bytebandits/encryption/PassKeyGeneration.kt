package bytebandits.encryption

import bytebandits.interfaces.PassKeyGenerator
import java.security.NoSuchAlgorithmException
import java.security.SecureRandom
import java.security.spec.InvalidKeySpecException
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec


public class PassKeyGeneration {

    companion object : PassKeyGenerator {
        override fun passwordGen(
            givenLen: Int?,
            digits: Boolean?,
            capitals: Boolean?,
            specialCharacters: Boolean?
        ): String {
            /* Returns password of user generated or default length.
            Specifications override default allowable characters*/

            //set password length
            var passLen = 14 //recommended strong password length
            if (givenLen != null) {
                passLen = givenLen //client given password length
            }

            //gather set of valid characters
            var characterList = "abcdefghijklmnopqrstuvwxyz"
            val digList = "1234567890"
            val capList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
            val spcList = "!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~"

            if (digits == null) { //if digits == null or true, include digits
                characterList += digList
            } else {
                if (digits) {
                    characterList += digList
                }
            }

            if (capitals == null) { //if capitals == null or true, include capitals
                characterList += capList
            } else {
                if (capitals) {
                    characterList += capList
                }
            }
            if (specialCharacters == null) { //if specialCharacters == null or true, include specialCharacters
                characterList += spcList
            } else {
                if (specialCharacters) {
                    characterList += spcList
                }
            }

            //build password
            var password = ""
            val rand = SecureRandom() //create secure random int
            var randInt: Int = rand.nextInt(characterList.length)
            for (i: Int in 1.rangeTo(passLen)) {
                password += characterList[randInt]
                randInt = rand.nextInt(characterList.length)
            }

            return password
        }

        @Throws(NoSuchAlgorithmException::class, InvalidKeySpecException::class)
        override fun passkeyGen(givenLen: Int?, password: String?, givenSalt: String?): ByteArray {
            /*Returns new passkey (optionally generated from password)*/

            //set password length
            var passLen = 32 //recommended strong password length in bytes
            if (givenLen != null) {
                passLen = givenLen //client given password length
            }

            //passkey not from password
            if (password == null) {
                return getRandomBytes(passLen)
            }

            //passkey from password with PBKDF2 algorithm
            val iterations = 120_000 //OWASP recommended iterations
            val chars = password.toCharArray()
            val salt: ByteArray =
                givenSalt?.toByteArray() ?: getRandomBytes(passLen) //salt = given salt or random bytes

            val spec = PBEKeySpec(chars, salt, iterations, passLen * 8) //key length in bits
            val skf = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1")
            var hash = skf.generateSecret(spec).encoded

            hash = salt.plus(hash) //if password is given, hash is returned with salt as first bits

            return hash
        }

        @Throws(NoSuchAlgorithmException::class)
        private fun getRandomBytes(size: Int): ByteArray {
            /*Returns <size> random bytes with SHA1PRNG algorithm*/
            val rand = SecureRandom.getInstance("SHA1PRNG")
            val bytes = ByteArray(size)
            rand.nextBytes(bytes)
            return bytes
        }

    }
}
