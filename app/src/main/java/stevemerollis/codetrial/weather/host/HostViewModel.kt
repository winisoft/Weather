package stevemerollis.codetrial.weather.host

import stevemerollis.codetrial.weather.host.HostViewModel.HostUiFlow
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import stevemerollis.codetrial.weather.settings.SharedPrefs
import stevemerollis.codetrial.weather.settings.app.PrefsStore
import javax.inject.Inject


@HiltViewModel
class HostViewModel
@Inject
constructor(
        val sharedPrefs: PrefsStore,
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