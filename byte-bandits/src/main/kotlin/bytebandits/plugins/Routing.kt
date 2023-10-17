package bytebandits.plugins

import bytebandits.encryption.encryptMe
import bytebandits.models.SimpleFileRequest
import bytebands.persistence.SimpleFilePersister
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.lang.Exception

val logger = LoggerFactory.getLogger("RoutesLogger")


//TODO : move the routes into their own classes files
fun Application.configureRouting() {
	routing {
		route("/storage/Submit", HttpMethod.Post){
			handle {
				//test code below, need to get the fields from http request
				try{
					val requestData = call.receive<SimpleFileRequest>()
					var saved = SimpleFilePersister.SimpleFilePersist(requestData)
					call.respondText (status = HttpStatusCode.OK ){ "This worked: $saved" }
				}catch (e: Exception) {
					call.respondText( status = HttpStatusCode.BadGateway, provider = { "This had an error" })
				}
			}
		}

		route("/storage/Get/{user}/{record_key}", HttpMethod.Get){
			handle {
				try {
					val user = call.parameters["user"] ?: "default_user"
					val recordKey = call.parameters["record_key"] ?: "default_key"

					val responseText = SimpleFilePersister.SimpleFileRetrieve(user, recordKey)
					call.respondText(status = HttpStatusCode.OK) { responseText }
				}catch (e: Exception){
					call.respondText( status = HttpStatusCode.BadGateway, provider = { "This had an error" })
				}
			}
		}
	}
}
