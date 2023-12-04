import bytebandits.models.PasswordEntry
import bytebandits.models.fromByteArray
import kotlin.test.Test
import kotlin.test.assertContentEquals
import kotlin.test.assertEquals

class PasswordEntryTest {

  @Test
  fun toByteArrayTest() {
    val passwordEntry = PasswordEntry("site", "username", "password")
    val expected = "site\nusername\npassword".toByteArray(Charsets.UTF_8)

    val result = passwordEntry.toByteArray()

    assertContentEquals(expected, result)
  }

  @Test
  fun toStringTest() {
    val passwordEntry = PasswordEntry("site", "username", "password")
    val expected = "Site: site\nUsername: username\nPassword: password\n"

    val result = passwordEntry.toString()

    assertEquals(expected, result)
  }
}

class FromByteArrayTest {

  @Test
  fun fromByteArrayTest() {
    val byteArray = "site\nusername\npassword".toByteArray(Charsets.UTF_8)
    val expected = PasswordEntry("site", "username", "password")

    val result = fromByteArray(byteArray)

    assertEquals(expected, result)
  }
}