package stevemerollis.codetrial.weather.distraction

import androidx.appcompat.app.AppCompatActivity

interface DriverDistractionListener {
    fun onDriverDistractionActive(clazz: Class<out AppCompatActivity>?)
    fun onDriverDistractionInactive()
}