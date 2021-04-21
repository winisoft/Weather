package stevemerollis.codetrial.weather.viewmodel

import dagger.hilt.android.scopes.ViewModelScoped
import dispatch.core.DispatcherProvider
import dispatch.core.IOCoroutineScope
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.Channel.Factory.UNLIMITED
import kotlinx.coroutines.flow.*
import stevemerollis.codetrial.weather.currently.view.CurrentlyLayoutModel
import stevemerollis.codetrial.weather.util.lo.logD


@ViewModelScoped
@OptIn(ExperimentalCoroutinesApi::class)
abstract class WeatherUseCase<T>
constructor(
) : UseCase<T> {




}