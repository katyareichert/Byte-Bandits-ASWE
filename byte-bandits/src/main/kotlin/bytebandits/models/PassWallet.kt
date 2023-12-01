package bytebandits.models

import kotlinx.serialization.Serializable

@Serializable
data class PasswordEntry(val site: String, val username: String, val password: String) {
  override fun toString(): String {
    return "Site: $site\nUsername: $username\nPassword: $password\n"
  }
}