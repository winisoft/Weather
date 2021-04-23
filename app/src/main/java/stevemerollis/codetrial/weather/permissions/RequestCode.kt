package stevemerollis.codetrial.weather.permissions

import androidx.annotation.IntDef

@Target(AnnotationTarget.FUNCTION, AnnotationTarget.LOCAL_VARIABLE,
    AnnotationTarget.VALUE_PARAMETER, AnnotationTarget.EXPRESSION)
@Retention(AnnotationRetention.SOURCE)
@IntDef(PermissionsUtil.CODE_ACCESS_LOCATION)
annotation class RequestCode