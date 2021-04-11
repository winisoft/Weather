package stevemerollis.codetrial.weather.vehicle.cal


interface CalibrationUtil {

    val papiUrl: String

    suspend fun getString(key: String): String?
}