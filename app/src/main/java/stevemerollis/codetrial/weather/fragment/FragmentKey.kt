package stevemerollis.codetrial.weather.fragment

import androidx.fragment.app.Fragment
import kotlin.reflect.KClass

@Target(
AnnotationTarget.FUNCTION,
AnnotationTarget.PROPERTY_GETTER,
AnnotationTarget.PROPERTY_SETTER)
@Retention(AnnotationRetention.RUNTIME)
internal annotation class FragmentKey(
    val value: KClass<out Fragment>
)