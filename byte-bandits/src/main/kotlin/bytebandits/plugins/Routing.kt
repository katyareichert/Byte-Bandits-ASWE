package bytebandits.plugins

import bytebandits.encryption.PassKeyGeneration
import bytebandits.models.SimpleFileRequest
import bytebandits.persistence.FilePersister
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.auth.*
import io.ktor.server.auth.jwt.*
import io.ktor.server.plugins.swagger.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory

val logger = LoggerFactory.getLogger("RoutesLogger")

// TODO : move the routes into their own classes files
@Suppress("LongMethod, CyclomaticComplexMethod")
fun Application.configureRouting() {
    routing {
        // This addds authentication, the route is now protected
        authenticate {
            route("/storage/Submit", HttpMethod.Post) {
                handle {
                    // test code below, need to get the fields from http request
                    try {
                        val requestData = call.receive<SimpleFileRequest>()
                        val principal = call.principal<JWTPrincipal>()
                        val clientId = principal!!.payload.getClaim("clientId").asString()
                        // val expiresAt = principal.expiresAt?.time?.minus(System.currentTimeMillis())
                        var saved = FilePersister.simpleFilePersist(requestData, "storage", clientId, true)
                        call.respondText(status = HttpStatusCode.OK) { "This worked: $saved" }
                    } catch (e: Exception) {
                        call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
                    }
                }
            }

            route("/storage/Get/{file_name}", HttpMethod.Get) {
                handle {
                    try {
                        val principal = call.principal<JWTPrincipal>()
                        val clientId = principal!!.payload.getClaim("clientId").asString()
                        val fileName = call.parameters["file_name"] ?: "default_key"

                        val responseText = FilePersister.simpleFileRetrieve(fileName, "storage", clientId, "")
                        call.respond(status = HttpStatusCode.OK) { responseText }
                    } catch (e: Exception) {
                        call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
                    }
                }
            }

            route("/storage/Delete", HttpMethod.Get) {
                handle {
                    try {
                        val principal = call.principal<JWTPrincipal>()
                        val clientId = principal!!.payload.getClaim("clientId").asString()
                        val fileName = call.parameters["file_name"] ?: "default_key"

                        val responseText = FilePersister.simpleFileDelete(fileName, "storage", clientId, "")
                        call.respond(status = HttpStatusCode.OK) { responseText }
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
                    call.respond(status = HttpStatusCode.OK) { responseText }
                } catch (e: Exception) {
                    call.respondText(status = HttpStatusCode.BadGateway, provider = { "This had an error" })
                }
            }
        }

        route("/virusChecker/", HttpMethod.Post) {
            handle {
                try {
                    // val user = call.parameters["client_id"] ?: "default_user"
                    // val recordKey = call.parameters["record_key"] ?: "default_key"

                    // val responseText = FilePersister.simpleFileRetrieve(user, recordKey)
                    call.respondText(status = HttpStatusCode.OK) { "responseText" }
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
