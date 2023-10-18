package bytebandits.encryption

import kotlin.random.Random

fun passGen(userLen: Int?, digits: Boolean?, capitals: Boolean?, specialCharacters: Boolean?): String{
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
    for (i: Int in 1.rangeTo(passLen)){
        password += characterList[Random.nextInt(from = 0, until = characterList.length)]
    }

    return password
}