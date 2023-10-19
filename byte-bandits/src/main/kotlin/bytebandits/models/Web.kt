package bytebandits.models

import kotlinx.serialization.Serializable

@Serializable
data class SimpleFileRequest(val contents: ByteArray, val userID: String?, val fileName: String) {
  override fun equals(other: Any?): Boolean {
    if (this === other) return true
    if (javaClass != other?.javaClass) return false

    other as SimpleFileRequest

    if (!contents.contentEquals(other.contents)) return false
    if (userID != other.userID) return false
    if (fileName != other.fileName) return false

    return true
  }

  override fun hashCode(): Int {
    var result = contents.contentHashCode()
    result = 31 * result + (userID?.hashCode() ?: 0)
    result = 31 * result + fileName.hashCode()
    return result
  }
}
