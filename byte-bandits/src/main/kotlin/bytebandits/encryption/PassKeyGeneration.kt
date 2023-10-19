package bytebandits.encryption

import bytebandits.interfaces.PassKeyGenerator
import java.security.SecureRandom

public class PassKeyGeneration {

    companion object: PassKeyGenerator {
        override fun passwordGen(givenLen: Int?, digits: Boolean?, capitals: Boolean?, specialCharacters: Boolean?): String {
            /* Returns password of user generated or default length. Specifications override default allowable characters*/

            //set password length
            var passLen = 14 //recommended strong password length
            if (givenLen != null) {
                passLen = givenLen //standard strong password length
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

        override fun passkeyGen(givenLen: Int?, password: String?, salt: String?): ByteArray {
            /*Returns new passkey (optionally generated from password)*/

            // TODO: implement PBKDF2(Password, Salt, PRF, c, dkLen)

            return byteArrayOf(0b00000000) //temp return
        }

    }
}