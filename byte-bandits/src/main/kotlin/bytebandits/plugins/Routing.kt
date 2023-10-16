package bytebandits.plugins

import bytebandits.encryption.encryptMe
import bytebands.persistence.saveDataToFile
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import org.slf4j.LoggerFactory
import java.util.logging.LogManager

val logger = LoggerFactory.getLogger("RoutesLogger")

fun Application.configureRouting() {
	routing {
		route("/storage/Submit", HttpMethod.Post){
			handle {
				val parameters =  call.parameters
				//test code below, need to get the fields from http request
				val encrypedData = encryptMe("Test", "test")
				logger.info("Hi there, welcome ${call.parameters}")
			}
		}

		route("/storage/Get", HttpMethod.Get){
			handle {
				val parameters =  call.parameters
				logger.info("Hi there, welcome ${call.parameters}")
				call.respondText("hello")
			}
		}
	}
}
