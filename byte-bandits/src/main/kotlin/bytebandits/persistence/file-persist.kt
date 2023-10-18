package bytebandits.persistence

import bytebandits.interfaces.SimplePersister
import bytebandits.models.SimpleFileRequest
import java.io.File
import java.util.*


public  class SimpleFilePersister {
	//This is a static method
	companion object : SimplePersister {
		override fun SimpleFilePersist(request: SimpleFileRequest): Boolean {
			// val dataEncrypt = encryptMe(request.contents, request.recordKey)

			// check operating system to get file path
			val osName: String = System.getProperty("os.name").lowercase(Locale.getDefault())
			val path: String = if (osName.indexOf("windows") > -1) { //get env for windows
				System.getenv("APPDATA")
			} else if (osName.indexOf("mac") > -1) { // get the env for Mac
				System.getenv("?")
			} else { //anything else
				System.getProperty("user.home")
			}

			// create directory at current path of name given by user
			val dir = File(path + File.separator + request.user)
			val file = File(request.user) //TODO: accept unique file names

			if (!dir.isDirectory)   { //check if directory exists
				if (!dir.mkdirs()) {  //create directory and check success
					return false //could not create directory
				}

				if (!file.exists()) { //check file existence
					if (!file.createNewFile()) { //create file and check success
						return false //could not create file
					}
				}

				//write to file without overwrite
				file.appendText(request.contents) //TODO: contents as byte array

			}

			//no errors raised in file/directory creation
			return true
		}

		override fun SimpleFileRetrieve(user: String, key: String): String {
			return "Hey, i'm a file"
		}
	}
}
