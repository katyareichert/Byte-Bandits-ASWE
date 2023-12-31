package bytebandits.persistence

import bytebandits.interfaces.Persister
import bytebandits.models.SimpleFileRequest
import java.io.File
import java.util.*


public class FilePersister {
    //This is a static method
    companion object : Persister {
        override fun simpleFilePersist(
            contents: ByteArray,
            userID: String?,
            fileName: String,
            endpointDirectory: String?,
            clientID: String?,
            overwrite: Boolean
        ): Boolean {

            val path = getPath(endpointDirectory, clientID, userID, true)

            val file = File(path + File.separator + fileName)

            if (!file.exists()) { //check file existence
                if (!file.createNewFile()) { //create file and check success
                    return false //could not create file
                }
            } else if (!overwrite) { //check if overwrite is allowed
                throw IllegalArgumentException("File already exists and overwrite is not allowed")
            }

            file.writeBytes(contents) //write contents to file

            //no errors raised in file/directory creation
            return true
        }

        override fun simpleFileRetrieve(
            fileName: String,
            endpointDirectory: String?,
            clientID: String?,
            userID: String?
        ): ByteArray {
            val path = getPath(endpointDirectory, clientID, userID, false)

            val file = File(path + File.separator + fileName)
            if (!file.exists()) {
                throw IllegalArgumentException("File does not exist")
            }
            return file.readBytes()
        }

        override fun simpleFileDelete(
            fileName: String,
            endpointDirectory: String?,
            clientID: String?,
            userID: String?
        ): Boolean {
            val path = getPath(endpointDirectory, clientID, userID, false)

            val file = File(path + File.separator + fileName)
            if (!file.exists()) {
                throw IllegalArgumentException("File does not exist")
            }
            return file.delete()
        }

        private fun getPath(endpointDirectory: String?, clientID: String?, userID: String?, create: Boolean): String {
            var path: String = getOSPath()

            // check if directory called SecurityTools exists and create if not
            var dir = File(path + File.separator + "SecurityTools")
            if (!dir.isDirectory) { //check if directory exists
                if (!dir.mkdirs()) {  //create directory and check success
                    throw IllegalArgumentException("Could not create directory")
                }
            }

            path = path + File.separator + "SecurityTools"

            if (endpointDirectory != null) {
                path = openCreateDir(path, endpointDirectory, create)
            }
            if (clientID != null) {
                path = openCreateDir(path, clientID, create)
            }
            if (userID != null && clientID == null) {
                throw IllegalArgumentException("Client ID must be specified to use user ID")
            } else if (userID != null) {
                path = openCreateDir(path, userID, create)
            }

            return path
        }

        private fun getOSPath(): String {
            val osName: String = System.getProperty("os.name").lowercase(Locale.getDefault())
            var path: String = if (osName.indexOf("windows") > -1) { //get env for windows
                System.getenv("APPDATA")
            } else if (osName.indexOf("mac") > -1) { // get the env for Mac
                System.getenv("HOME")
            } else { //anything else
                System.getProperty("user.home")
            }
            return path
        }

        private fun openCreateDir(path: String, dir: String, create: Boolean): String {
            var pathNew: String = path + File.separator + dir
            val dirNew = File(pathNew)
            if (!dirNew.isDirectory) { //check if directory exists
                if (!create) {
                    throw IllegalArgumentException("Directory does not exist")
                }
                if (!dirNew.mkdirs()) {  //create directory and check success
                    throw IllegalArgumentException("Could not create directory")
                }
            }
            return pathNew
        }
    }
}
