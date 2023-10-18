package bytebands.persistence

import bytebandits.encryption.encryptMe
import bytebandits.interfaces.SimplePersister
import bytebandits.models.SimpleFileRequest

public  class SimpleFilePersister {
	//This is a static method
	companion object : SimplePersister {
		override fun SimpleFilePersist(request: SimpleFileRequest): Boolean {
			val dataEncrypt = encryptMe(request.contents, request.recordKey)
			return true
		}

		override fun SimpleFileRetrieve(user: String, key: String): String {
			return "Hey, i'm a file"
		}
	}
}
