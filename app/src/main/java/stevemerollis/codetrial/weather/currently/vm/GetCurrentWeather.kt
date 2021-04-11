package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.scopes.ViewModelScoped
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import javax.inject.Inject

@ViewModelScoped
class GetCurrentWeather
@Inject
constructor(
    val currentlyRepository: CurrentlyRepository
) {

    operator fun invoke() {

    }
}