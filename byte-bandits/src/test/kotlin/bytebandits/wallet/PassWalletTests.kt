package bytebandits.wallet

import bytebandits.models.PasswordEntry
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class PassWalletTest {

  @Test
  fun passStoreTest() {
    val passwordEntry = PasswordEntry("site", "username", "password")
    val clientID = "clientID"
    val userID = "userID"

    val result = PassWallet.passStore(passwordEntry, clientID, userID)

    assertTrue(result)

    val result2 = PassWallet.passStore(passwordEntry, clientID, null)

    assertTrue(result2)
  }

  @Test
  fun passRetrieveTest() {
    val passwordEntry = PasswordEntry("site", "username", "password")
    val clientID = "clientID"
    val userID = "userID"
    val site = "site"

    PassWallet.passStore(passwordEntry, clientID, userID)
    val result = PassWallet.passRetrieve(site, clientID, userID)

    assertEquals(result, PasswordEntry(site, "username", "password").toString())
  }
}