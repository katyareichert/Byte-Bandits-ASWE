package bytebandits.tokengen

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import java.util.*

fun main(){
	val token = JWT.create()
		.withAudience("jwt-audience")
		.withIssuer("https://jwt-provider-domain/")
		.withClaim("clientId", "sample")
		.withExpiresAt(Date(System.currentTimeMillis() + 600000))
		.sign(Algorithm.HMAC256("secret"))
	print(token)
}
