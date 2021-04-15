package stevemerollis.codetrial.weather.host

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import stevemerollis.codetrial.weather.settings.app.WeatherPreferences
import javax.inject.Inject


@HiltViewModel
class HostViewModel
@Inject
constructor(
    val savedState: SavedStateHandle
): ViewModel() {

//    private val uiModelFlow = combine(
//        sharedPrefs.isNightMode()
//    ) {
//        return@combine CourseUiModel(
//            courses = filterCourses(courses, filterOption),
//            filter = filterOption.filter
//        )
//    }

    sealed class HostUiFlow {

    }
}