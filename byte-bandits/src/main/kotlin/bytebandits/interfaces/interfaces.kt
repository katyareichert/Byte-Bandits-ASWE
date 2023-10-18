package bytebandits.interfaces

import bytebandits.models.SimpleFileRequest

interface SimplePersister{
	fun SimpleFilePersist(request: SimpleFileRequest): Boolean
	fun SimpleFileRetrieve(user: String, key: String): String
}

interface SimpleEncrypter{
	fun SimpleFileEncrypt(contents:String, key: String?): Boolean
	fun SimpleFileDecrypt(contents: String, key: String?, scheme:String): String
	fun SimpleFileDecrypt(contents: ByteArray, key: String?, scheme:String): ByteArray
}
