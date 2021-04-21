package stevemerollis.codetrial.weather.async

import stevemerollis.codetrial.weather.R
import java.io.IOException
import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import stevemerollis.codetrial.weather.main.MainActivity

sealed class ViewModelState<out T> {


    data class Success<out T>(val data: T): ViewModelState<T>() {
        override fun toString(): String = "Success"
    }


}