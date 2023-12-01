package bytebandits.interfaces

import bytebandits.models.PasswordEntry
import bytebandits.models.SimpleFileRequest
import java.io.File

interface Persister {
    fun simpleFilePersist(
        contents: ByteArray,
        userID: String?,
        fileName: String,
        endpointDirectory: String?,
        clientID: String?,
        overwrite: Boolean
    ): Boolean

    fun simpleFileRetrieve(fileName: String, endpointDirectory: String?, clientID: String?, userID: String?): ByteArray
    fun simpleFileDelete(fileName: String, endpointDirectory: String?, clientID: String?, userID: String?): Boolean
}

interface Encrypter {
    fun fileEncrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray
    fun fileEncrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray
    fun fileDecrypt(contents: ByteArray, key: ByteArray?, scheme: String?): ByteArray
    fun fileDecrypt(contents: ByteArray, password: String?, scheme: String?): ByteArray
}

interface PassKeyGenerator {
    fun passwordGen(givenLen: Int?, digits: Boolean?, capitals: Boolean?, specialCharacters: Boolean?): String
    fun passkeyGen(givenLen: Int?, password: String?, givenSalt: String?): ByteArray
}

interface VirusChecker {
    fun virusCheck(request: SimpleFileRequest): String
    fun reportVirus(file: File): String
}

interface KeyGenerator {
    fun generateKey(seed: String, appSecret: Boolean, salt: String?): ByteArray
}

interface HashCheckNStore {
    fun hashCheck(request: SimpleFileRequest): String
    fun hashStore(request: SimpleFileRequest, clientID: String): String
    fun hashRetrieve(hash: String, clientID: String, userID: String?): ByteArray
}

interface PassWallet {
    fun passStore(passwordEntry: PasswordEntry, clientID: String, userID: String?): Boolean
    fun passRetrieve(clientID: String, userID: String?): String
}