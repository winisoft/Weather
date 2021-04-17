package stevemerollis.codetrial.weather.app

import dagger.Binds
import dagger.Provides
import dispatch.core.*
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Singleton
import kotlin.coroutines.CoroutineContext

@Singleton
class AppCoroScope
@Inject
constructor()
: CoroutineScope {

    private val job: Job = SupervisorJob()

    private val handleException: (CoroutineContext, Throwable?) -> Unit = { _, _ ->}

    private val exceptionHandler = CoroutineExceptionHandler(handleException)

    override val coroutineContext: CoroutineContext
        get() = job + exceptionHandler
}