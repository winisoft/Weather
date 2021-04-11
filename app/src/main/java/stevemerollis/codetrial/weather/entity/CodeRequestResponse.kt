package stevemerollis.codetrial.weather.entity

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class CodeRequestResponse(
        val profileId: String,
        val expirationDateTime: Long,
        val allowedAttempts: Int,
        val channel: String
)
