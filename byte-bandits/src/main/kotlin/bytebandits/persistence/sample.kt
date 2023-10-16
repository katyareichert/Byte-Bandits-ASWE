package bytebands.persistence

import bytebandits.encryption.encryptMe

fun saveDataToFile(data: String, user: String, dataKey: String): Boolean{
	val dataEncrypt = encryptMe(data, dataKey)
	return true
}


fun getMeAFile(userName: String, dataKey: String): String{
	return "Hey, i'm a file"
}
