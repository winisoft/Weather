package stevemerollis.codetrial.weather.permissions

import android.Manifest
import androidx.annotation.StringDef

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
@StringDef(Manifest.permission.ACCESS_FINE_LOCATION)
annotation class PermissionTypes