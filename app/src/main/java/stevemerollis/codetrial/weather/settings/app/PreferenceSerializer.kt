package stevemerollis.codetrial.weather.settings.app

//import stevemerollis.codetrial.weather.proto.*
//import androidx.datastore.core.CorruptionException
//import androidx.datastore.core.Serializer
//import androidx.datastore.preferences.protobuf.InvalidProtocolBufferException
//import java.io.InputStream
//import java.io.OutputStream
//
//object PreferenceSerializer: Serializer<WeatherPreferences> {
//
//    override suspend fun readFrom(input: InputStream): WeatherPreferences {
//        try {
//            return WeatherPreferences.parseFrom(input)
//        } catch (ipbe: InvalidProtocolBufferException) {
//            throw CorruptionException("Failed to read proto", ipbe)
//        }
//    }
//
//    override fun writeTo(t: WeatherPreferences, output: OutputStream) = t.writeTo(output)
//
//    override val defaultValue: WeatherPreferences
//        get() = TODO("Not yet implemented")
//}