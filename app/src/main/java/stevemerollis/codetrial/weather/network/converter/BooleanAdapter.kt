package stevemerollis.codetrial.weather.network.converter

import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.ToJson




class BooleanAdapter {

    @FromJson
    @BooleanAdapting
    fun fromJson(jsonReader: JsonReader): Boolean {

        return when (jsonReader.peek()) {
            JsonReader.Token.NULL -> false
            JsonReader.Token.STRING -> jsonReader.nextString()!!.toBoolean()
            JsonReader.Token.BOOLEAN -> jsonReader.nextBoolean()
            else -> false
        }
    }

    @ToJson
    fun toJson(@BooleanAdapting value: Boolean): String {
        return value.toString()
    }

}