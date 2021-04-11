package stevemerollis.codetrial.weather.validate.rules.business

import com.squareup.moshi.Json

class ConfirmCodeResponse(
        @Json(name = "access_token") val accessToken: String,
        @Json(name = "scope") val scope: String,
        @Json(name = "expires_in") val expiresIn: Int
)