package stevemerollis.codetrial.weather.currently.vm

import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.currently.CurrentlyContract
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import javax.inject.Inject

@ViewModelScoped
class GetCurrentWeather
@Inject
constructor(
    val currentlyRepository: CurrentlyRepository
) {

    operator fun invoke(): Flow<Model<CurrentlyViewProperties>> = flow {

    }
}