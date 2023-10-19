package bytebandits.interfaces

import bytebandits.models.SimpleFileRequest

interface SimplePersister{
	fun SimpleFilePersist(request: SimpleFileRequest): Boolean
	fun SimpleFileRetrieve(user: String, key: String): String
}

interface Encrypter{
	fun FileEncrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray
	fun FileEncrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray
	fun FileDecrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray
	fun FileDecrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray
}

interface PassKeyGenerator {
	fun passwordGen(givenLen: Int?, digits: Boolean?, capitals: Boolean?, specialCharacters: Boolean?): String
	fun passkeyGen (givenLen: Int?, password: String?, salt: String?): ByteArray
}