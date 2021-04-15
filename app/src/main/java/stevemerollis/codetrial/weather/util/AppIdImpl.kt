package stevemerollis.codetrial.weather.util

import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AppIdImpl
@Inject
constructor()
    : AppIdUtil {

    override fun getApiToken(): String = ""
}