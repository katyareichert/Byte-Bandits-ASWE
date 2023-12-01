package bytebandits.wallet

import bytebandits.interfaces.PwdWallet
import bytebandits.models.PasswordEntry
import bytebandits.models.fromByteArray
import bytebandits.persistence.FilePersister.Companion.simpleFilePersist
import bytebandits.persistence.FilePersister.Companion.simpleFileRetrieve
import kotlinx.serialization.json.*

public class PassWallet {
  companion object : PwdWallet {
    override fun passStore(passwordEntry: PasswordEntry, clientID: String, userID: String?): Boolean {
      val name = passwordEntry.site
      val contents = passwordEntry.toByteArray()
      val result = simpleFilePersist(contents, userID, name, "passwallet", clientID, true)
      return result
    }

    override fun passRetrieve(site: String, clientID: String, userID: String?): String {
      val result = simpleFileRetrieve(site, "passwallet", clientID, userID)
      val entry = fromByteArray(result)
      return entry.toString()
    }
  }
}