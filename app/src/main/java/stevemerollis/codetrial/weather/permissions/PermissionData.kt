package stevemerollis.codetrial.weather.permissions

//Warning can be ignored because we are not comparing array data
/**
 * Data class representing the results of a prompt for the user's authorization to employ dangerous features
 */
@Suppress("ArrayInDataClass")
data class PermissionData(val requestCode: Int, val permissions: Array<String>, val result: IntArray)