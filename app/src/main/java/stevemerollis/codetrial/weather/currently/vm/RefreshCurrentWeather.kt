package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.scopes.ViewModelScoped
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import javax.inject.Inject

@ViewModelScoped
class RefreshCurrentWeather
@Inject
constructor(
    private val currentlyRepository: CurrentlyRepository
){
    suspend operator fun invoke() = currentlyRepository.refresh()
}