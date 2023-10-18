package bytebandits.encryption

import kotlin.random.Random

fun passGen(userLen: Int?, digits: Boolean, capitals: Boolean, specialCharacters: Boolean): String{
    /* Returns password of user generated or default length. Minimally includes lowercase letters.*/

    //set password length
    var passLen = 12
    if (userLen != null) {
        passLen = userLen //standard strong password length
    }

    //gather set of valid characters
    var characterList = "abcdefghijklmnopqrstuvwxyz"
    val digList = "1234567890"
    val capList = "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    val spcList = "!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~"
    if (digits) {
        characterList += digList
    }
    if (capitals) {
        characterList += capList
    }
    if (specialCharacters) {
        characterList += spcList
    }

    //build password
    var password = ""
    for (i: Int in 1.rangeTo(passLen)){
        password += characterList[Random.nextInt(from = 0, until = characterList.length)]
    }

    return password
}