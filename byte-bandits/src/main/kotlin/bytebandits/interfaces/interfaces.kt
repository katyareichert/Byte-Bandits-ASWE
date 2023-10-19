package bytebandits.interfaces

import bytebandits.models.SimpleFileRequest

interface Persister{
	fun simpleFilePersist(request: SimpleFileRequest, endpointDirectory: String?, clientID: String?, overwrite: Boolean): Boolean
	fun simpleFileRetrieve(fileName: String, endpointDirectory: String?, clientID: String?, userID: String?): ByteArray
	fun simpleFileDelete(fileName: String, endpointDirectory: String?, clientID: String?, userID: String?): Boolean
}

interface Encrypter{
	fun fileEncrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray
	fun fileEncrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray
	fun fileDecrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray
	fun fileDecrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray
}
