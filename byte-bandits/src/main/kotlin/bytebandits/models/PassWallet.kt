package bytebandits.models

import kotlinx.serialization.Serializable

@Serializable
data class PasswordEntry(val site: String, val username: String, val password: String) {
  override fun toString(): String {
    return "Site: $site\nUsername: $username\nPassword: $password\n"
  }

  fun toByteArray(): ByteArray {
    val entryString = "$site\n$username\n$password"
    return entryString.toByteArray(Charsets.UTF_8)
  }
}

fun fromByteArray(byteArray: ByteArray): PasswordEntry {
  val entryString = byteArray.toString(Charsets.UTF_8)
  val entryList = entryString.split("\n")
  return PasswordEntry(entryList[0], entryList[1], entryList[2])
}