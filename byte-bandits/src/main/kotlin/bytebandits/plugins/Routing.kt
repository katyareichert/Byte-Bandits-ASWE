package bytebandits.plugins

import bytebandits.encryption.PassKeyGeneration
import bytebandits.encryption.EncKeyGeneration
import bytebandits.encryption.Encryption
import bytebandits.hashes.Hashes
import bytebandits.interfaces.HashCheckNStore
import bytebandits.interfaces.PwdWallet
import bytebandits.models.PasswordEntry
import bytebandits.models.SimpleFileRequest
import bytebandits.models.WebSimpleFileRequest
import bytebandits.persistence.FilePersister
import bytebandits.viruscheck.VirusHashChecker
import bytebandits.wallet.PassWallet
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.util.*

val logger = LoggerFactory.getLogger("RoutesLogger")


//TODO : move the routes into their own classes files
fun Application.configureRouting() {
	routing {
		//This addds authentication, the routes are now protected
		authenticate {
			route("/storage/Submit/", HttpMethod.Post) {
				handle {
					//test code below, need to get the fields from http request
					try {
						val requestData = call.receive<WebSimpleFileRequest>()
						val request = SimpleFileRequest(
							Base64.getDecoder().decode(requestData.contents),
							requestData.userID,
							requestData.fileName
						)
						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()
						val userId = request.userID
						val fileName = request.fileName

						val encryptionKey = EncKeyGeneration.generateKey(clientId, true, null)
						val encrypted = Encryption.fileEncrypt(request.contents, encryptionKey, "AES")
						val saved = FilePersister.simpleFilePersist(encrypted, userId, fileName,"storage", clientId, true)

						call.respondText(status = HttpStatusCode.OK) { "This worked: $saved" }
					} catch (e: Exception) {
						logger.error("error serving request", e)
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}

			route("/storage/Get/{fileName}/{userId?}", HttpMethod.Get) {
				handle {
					try {
						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()
						val fileName = call.parameters["fileName"]!!
						val userId = call.parameters["userId"]

						val encrypted = FilePersister.simpleFileRetrieve(fileName, "storage", clientId, userId)
						val encryptionKey = EncKeyGeneration.generateKey(clientId, true, null)
						val decrypted = Encryption.fileDecrypt(encrypted, encryptionKey, "AES")

						call.respondBytes(status = HttpStatusCode.OK, provider = { decrypted })
					} catch (e: Exception) {
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}

			route("/storage/Delete/{fileName}/{userId?}", HttpMethod.Get) {
				handle {
					try {

						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()
						val fileName = call.parameters["fileName"]!!
						val userId = call.parameters["userId"]

						val responseText = FilePersister.simpleFileDelete(fileName, "storage", clientId, userId)
						call.respondText(status = HttpStatusCode.OK) { responseText.toString() }
					} catch (e: Exception) {
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}
			route("/file/encrypt/{b64key?}/{scheme?}", HttpMethod.Post) {
				handle {
					//test code below, need to get the fields from http request
					try {
						val requestData = call.receive<WebSimpleFileRequest>()
						val request = SimpleFileRequest(
							Base64.getDecoder().decode(requestData.contents),
							requestData.userID,
							requestData.fileName
						)
						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()

						var b64key = call.parameters["b64key"]
						var scheme = call.parameters["scheme"]

						var key: ByteArray

						if (b64key.isNullOrEmpty()) {
							key = EncKeyGeneration.generateKey(clientId, true, null)
						} else {
							val decodedKey = b64key.replace('-', '+').replace('_', '/')
							key = Base64.getDecoder().decode(decodedKey)
						}
						if (scheme.isNullOrEmpty()) {
							scheme = "AES"
						}
						val encrypted = Encryption.fileEncrypt(request.contents, key, scheme)
						call.respondBytes(status = HttpStatusCode.OK, provider = { encrypted })
					} catch (e: Exception) {
						logger.error("error serving request", e)
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}
			route("/file/decrypt/{b64key?}/{scheme?}", HttpMethod.Post) {
				handle {
					//test code below, need to get the fields from http request
					try {
						val requestData = call.receive<WebSimpleFileRequest>()
						val request = SimpleFileRequest(
							Base64.getDecoder().decode(requestData.contents),
							requestData.userID,
							requestData.fileName
						)
						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()

						var b64key = call.parameters["b64key"]
						var scheme = call.parameters["scheme"]
						var key: ByteArray

						if (b64key.isNullOrEmpty()) {
							key = EncKeyGeneration.generateKey(clientId, true, null)
						} else {
							val decodedKey = b64key.replace('-', '+').replace('_', '/')
							key = Base64.getDecoder().decode(decodedKey)
						}
						if (scheme.isNullOrEmpty()) {
							scheme = "AES"
						}

						val decrypted = Encryption.fileDecrypt(request.contents, key, scheme)
						call.respondBytes(status = HttpStatusCode.OK, provider = { decrypted })
					} catch (e: Exception) {
						logger.error("error serving request", e)
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}
			route("/hash/Check/{hash}", HttpMethod.Post) {
				handle {
					try {
						val requestData = call.receive<WebSimpleFileRequest>()
						val request = SimpleFileRequest(
							Base64.getDecoder().decode(requestData.contents),
							requestData.userID,
							requestData.fileName
						)

						val calculatedHash = Hashes.hashCheck(request)
						val responseText = if (calculatedHash == call.parameters["hash"]) {
							"Hashes match"
						} else {
							"Hashes do not match"
						}
						call.respondText(status = HttpStatusCode.OK) { responseText }
					} catch (e: Exception) {
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}

			route("/hash/Store/", HttpMethod.Post) {
				handle {
					try {
						val requestData = call.receive<WebSimpleFileRequest>()
						val request = SimpleFileRequest(
							Base64.getDecoder().decode(requestData.contents),
							requestData.userID,
							requestData.fileName
						)
						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()

						val responseText = Hashes.hashStore(request, clientId)
						call.respondText(status = HttpStatusCode.OK) { responseText }
					} catch (e: Exception) {
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}

			route("/hash/Get/", HttpMethod.Post) {
				handle {
					try {
						val requestData = call.receive<WebSimpleFileRequest>()
						val request = SimpleFileRequest(
							Base64.getDecoder().decode(requestData.contents),
							requestData.userID,
							requestData.fileName
						)
						val responseText = Hashes.hashCheck(request)

						call.respondText(status = HttpStatusCode.OK) { responseText }
					} catch (e: Exception) {
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}

			route("/hash/Retrieve/{userID}/{hash}", HttpMethod.Get) {
				handle {
					try {
						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()
						val userID = call.parameters["userID"]

						val responseText = Hashes.hashRetrieve(call.parameters["hash"]!!, clientId, userID)
						call.respondBytes(status = HttpStatusCode.OK, provider = { responseText })
					} catch (e: Exception) {
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}

			route("/pwdWallet/Store/{site}/{username}/{password}/{userID?}", HttpMethod.Get) {
				handle {
					try {
						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()
						val site = call.parameters["site"]!!
						val username = call.parameters["username"]!!
						val password = call.parameters["password"]!!
						val userID = call.parameters["userID"]

						val entry = PasswordEntry(site, username, password)
						PassWallet.passStore(entry, clientId, userID)
						call.respondText(status = HttpStatusCode.OK) { "Password saved" }
					} catch (e: Exception) {
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}

			route("/pwdWallet/Retrieve/{site}/{userID?}", HttpMethod.Get) {
				handle {
					try {
						val principal = call.principal<JWTPrincipal>()
						val clientId = principal!!.payload.getClaim("clientId").asString()
						val site = call.parameters["site"]!!
						val userID = call.parameters["userID"]

						val responseText = PassWallet.passRetrieve(site, clientId, userID)
						call.respondText(status = HttpStatusCode.OK) { responseText }
					} catch (e: Exception) {
						call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
					}
				}
			}
		}
		route("/password/Get/{len?}/{digits?}/{case?}/{specialChars?}", HttpMethod.Get) {
			handle {
				try {
					val len = call.parameters["len"]?.toInt() ?: 14
					val digits = call.parameters["digits"]?.toBoolean() ?: false
					val casing = call.parameters["case"]?.toBoolean() ?: false
					val specialChars = call.parameters["specialChars"]?.toBoolean() ?: false

					val pwd = PassKeyGeneration.passwordGen(len, digits, casing, specialChars)

					call.respondText(status = HttpStatusCode.OK) { pwd }
				} catch (e: Exception) {
					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
				}
			}
		}

		route("/passKey/Get/{len?}/{password?}/{salt?}", HttpMethod.Get) {
			handle {
				try {
					val len = call.parameters["len"]?.toInt()
					val password = call.parameters["password"]
					val salt = call.parameters["salt"]

					val responseText = PassKeyGeneration.passkeyGen(len, password, salt)
					call.respondText(status = HttpStatusCode.OK) { responseText.toString(Charsets.UTF_8) }
				} catch (e: Exception) {
					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
				}
			}
		}

		route("/virusChecker/CheckFile/", HttpMethod.Post) {
			handle {
				try {
					val requestData = call.receive<WebSimpleFileRequest>()
					val request = SimpleFileRequest(
						Base64.getDecoder().decode(requestData.contents),
						requestData.userID,
						requestData.fileName
					)
					val saved = VirusHashChecker.virusCheck(request)
					call.respondText(status = HttpStatusCode.OK) { saved }
				} catch (e: Exception) {
					call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
				}
			}
		}

		swaggerUI(path = "swagger", swaggerFile = "openapi/documentation.yaml") {
			version = "4.15.5"
		}
	}
}



