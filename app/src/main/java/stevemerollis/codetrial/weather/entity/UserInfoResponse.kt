package stevemerollis.codetrial.weather.contact.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
class UserInfoResponse(
        @Json(name = "userInfo") val userInfo: UserInfo
)

@Json(name = "userInfo")
class UserInfo(
    @Json(name = "accountOwner") val accountOwner: AccountOwner
)

class AccountOwner(
        val id: String,
        @Json(name = "fname") val firstName: String,
        @Json(name = "lname") val lastName: String,
        val preferredLanguageCode: String,
        val email: String,
        val phone: String?,
        val mobilePhone: String?,
        val workPhone: String?,
        val type: String
)