package bytebandits.encryption

import java.security.SecureRandom

fun passwordGen(userLen: Int?, digits: Boolean?, capitals: Boolean?, specialCharacters: Boolean?): String{
    /* Returns password of user generated or default length. Specifications override default allowable characters*/

    //set password length
    var passLen = 14 //recommended strong password length
    if (userLen != null) {
        passLen = userLen //standard strong password length
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
    for (i: Int in 1.rangeTo(passLen)){
        password += characterList[randInt]
        randInt = rand.nextInt(characterList.length)
    }

    return password
}

fun passkeyGen (passLen: Int?, password: String?): ByteArray{
    /*Returns new passkey*/

    //  PBKDF2(Password, Salt, PRF, c, dkLen)
    return byteArrayOf(0b00000000) //temp return
}