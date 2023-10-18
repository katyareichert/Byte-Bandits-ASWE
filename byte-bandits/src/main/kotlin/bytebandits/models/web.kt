package bytebandits.models

import kotlinx.serialization.Serializable

@Serializable
data class SimpleFileRequest(val contents: String, val user: String, val recordKey: String)
