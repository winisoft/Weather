package stevemerollis.codetrial.weather.app

import dispatch.core.DispatcherProvider
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

    private val handleException: (CoroutineContext, Throwable?) -> Unit = { ctx, thr ->}

    private val exceptionHandler = CoroutineExceptionHandler(handleException)

    override val coroutineContext: CoroutineContext
        get() = job + exceptionHandler
}