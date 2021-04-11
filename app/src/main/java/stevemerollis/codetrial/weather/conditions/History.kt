package stevemerollis.codetrial.weather.conditions

import android.os.Parcelable
import androidx.annotation.Keep
import com.squareup.moshi.Json
import kotlinx.parcelize.Parcelize

@Parcelize
@Keep
data class History(
    val message: String,
    /**
     * Http response code
     * ex: 200
     */
    @Json(name = "cod") val code: String,

    /**
     * ex: 2885679
     */
    @Json(name = "city_id") val cityId: Int,
    /**
     * Presumably, statistics on the duration of the calculation needed to procure this datum
     */
    @Json(name = "calctime") val calculationTime: Double,
    /**
     * Number of lines returned by this API call
     */
    @Json(name = "cnt") val count: Int
    //,
    //val list: List<> TODO: fix autogen
) : Parcelable