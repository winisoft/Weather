package stevemerollis.codetrial.weather.network.converter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonQualifier
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson


@JsonQualifier
@Retention(AnnotationRetention.RUNTIME)
annotation class EnsuresBoolean

class BooleanAdapter {

    @FromJson
    @EnsuresBoolean
    fun fromJson(jsonReader: JsonReader): Boolean {

        return when (jsonReader.peek()) {
            JsonReader.Token.NULL -> false
            JsonReader.Token.STRING -> jsonReader.nextString()!!.toBoolean()
            JsonReader.Token.BOOLEAN -> jsonReader.nextBoolean()
            else -> false
        }
    }

    @ToJson
    fun toJson(@EnsuresBoolean value: Boolean): String {
        return value.toString()
    }

}