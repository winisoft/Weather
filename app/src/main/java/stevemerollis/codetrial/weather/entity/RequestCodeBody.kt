package stevemerollis.codetrial.weather.entity

import com.squareup.moshi.Json
import java.util.*

class RequestBody(
        @Json(name = "assertion") var assertion: String,
        @Json(name = "device_id") var deviceId: String
) {
        //identifier granted to app that is requesting an access token, if different from 'client_id'
        //@Json(name = "end_use_client_id") val endUseClientId: String = "example: kf84hl9fk2lfld0ksmnvnbz",
        @Json(name = "client_id")
        var clientId: String = "AUTHENTICATOR_V2"

        @Json(name = "grant_type")
        var grantType: String = "urn:onstar:params:oauth:grant-type:jwt-bearer-otp"

        @Json(name = "scope")
        var scope: String = "priv onstar"

        @Json(name = "nonce")
        var nonce: String = UUID.randomUUID().toString()

        @Json(name = "timestamp")
        var timestamp: String = //ISO formatted; GMT
                String.format("%tFT%<tRZ", Calendar.getInstance(TimeZone.getTimeZone("Z")))
}