package bytebandits.encryption

import kotlin.test.*

class PassKeyGenerationTests {

    @Test
    fun `test passwordGen with all parameters utilized`(){
        /*Test utilization of all parameters (in valid cases)*/

        //All true
        val givenLen1 = 10
        val digits1 = true
        val capitals1 = true
        val specialCharacters1 = true
        val password1 = PassKeyGeneration.passwordGen(givenLen1,digits1,capitals1,specialCharacters1)

        assertEquals(password1.length, givenLen1)

        //All false
        val givenLen2 = 1
        val digits2 = false
        val capitals2 = false
        val specialCharacters2 = false
        val password2 = PassKeyGeneration.passwordGen(givenLen2,digits2,capitals2,specialCharacters2)

        assertEquals(password2.length, givenLen2)
        assertFalse("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(password2))
        assertFalse("1234567890".contains(password2))
        assertFalse("!\"#\$%&'()*+,-./:;<=>?@[\\]^_`{|}~".contains(password2))

        //Mixed bag
        val givenLen3 = 30
        val digits3 = false
        val capitals3 = true
        val specialCharacters3 = true
        val password3 = PassKeyGeneration.passwordGen(givenLen3,digits3,capitals3,specialCharacters3)

        assertFalse("1234567890".contains(password3))
        assertEquals(password3.length, givenLen3)
    }

    @Test
    fun `test passwordGen with some utilized parameters`(){
        val givenLen1 = 10
        val capitals1 = false
        val password1 = PassKeyGeneration.passwordGen(givenLen1, null, capitals1, null)

        assertEquals(password1.length, givenLen1)
        assertFalse("ABCDEFGHIJKLMNOPQRSTUVWXYZ".contains(password1))
    }

    @Test
    fun `test passwordGen with no parameters`(){
        val defaultLength = 14
        val password1 = PassKeyGeneration.passwordGen(null, null, null, null)

        assertEquals(password1.length, defaultLength)
    }

    @Test
    fun `test passkeyGen with no parameters`(){
        val defaultSize1 = 32
        val passkey1 = PassKeyGeneration.passkeyGen(null, null, null)

        assertEquals(passkey1.size, defaultSize1)
    }

    @Test
    fun `test passkeyGen with given password and salt`(){
        val passkeySize1 = 24
        val password1 = "very strong password"
        val salt1 = "salt salt salt"
        val passkey1 = PassKeyGeneration.passkeyGen(passkeySize1, password1, salt1)

        assertEquals(salt1.toByteArray().size + passkeySize1, passkey1.size)
    }

    @Test
    fun `test passkeyGen with given password and no salt given`(){
        val passkeySize1 = 24
        val password1 = "very strong password"
        val passkey1 = PassKeyGeneration.passkeyGen(passkeySize1, password1, null)

        assertEquals(2 * passkeySize1, passkey1.size)
    }

    @Test
    fun `test passkeyGen with no password and given salt`(){
        val passkeySize1 = 24
        val salt = "salt makes the passkey stronger"
        val passkey1 = PassKeyGeneration.passkeyGen(passkeySize1, null, salt)

        assertEquals(passkeySize1, passkey1.size)
    }
}
