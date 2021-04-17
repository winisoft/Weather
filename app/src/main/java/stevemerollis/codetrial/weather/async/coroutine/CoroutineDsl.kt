@file:Suppress("unused")

package stevemerollis.codetrial.weather.async.coroutine

import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

object CoroutineDsl {

    /**
     * Logger for coroutines: prints the thread on which this is taking place before the message
     */
    fun logC(message: () -> String?) = println("Thread': \t${Thread.currentThread().name}]\t${message()}")
    fun logC(throwable: Throwable?, message: () -> String): Unit = println("Thread: [${Thread.currentThread().name}]" +
        "\n\t${message()} -- ${throwable?.message}\n\t" +
            if (throwable?.stackTrace == null){ "no stack trace" }
            else throwable.stackTrace
                .forEach { it.toString() }
        )

    val io: CoroutineDispatcher = Dispatchers.IO
    val ui: CoroutineDispatcher = Dispatchers.Main
    val bg: CoroutineDispatcher = Dispatchers.Default

    /**
     * Launches a coroutines Job on the main thread, after cancelling the ongoing jo
     */
    fun uiJob(
        scope: CoroutineScope = CoroutineScope(Job()),
        block: suspend CoroutineScope.() -> Unit
    ): Unit = startJob(scope, ui, block)

    /**
     * Starts an async coroutine on the main thread
     */
    suspend fun <T> uiTask(block: suspend CoroutineScope.() -> T): T = startTask(ui, block)

    /**
     * Launches a coroutines Job for an IO function
     */
    fun ioJob(
        scope: CoroutineScope = CoroutineScope(Job()),
        block: suspend CoroutineScope.() -> Unit
    ): Unit = startJob(scope, io, block)

    /**
     * Launches an async coroutine for IO functions
     */
    suspend fun <T> ioTask(block: suspend CoroutineScope.() -> T): T = startTask(io, block)

    /**
     * Starts an async coroutine for IO functions
     */
    fun <T> ioTaskAsync(
            scope: CoroutineScope = CoroutineScope(Job()),
            block: suspend CoroutineScope.() -> T
    ): Deferred<T> = startTaskAsync(scope, io, block)

    /**
     * Launches a coroutines Job on the default background thread
     */
    fun bgJob(
            scope: CoroutineScope = CoroutineScope(Job()),
            block: suspend CoroutineScope.() -> Unit
    ): Unit = startJob(scope, bg, block)

    /**
     * Launches an async coroutine on the default background thread
     */
    suspend fun <T> bgTask(
           block: suspend CoroutineScope.() -> T)
    : T = startTask(bg, block)

    /**
     * Starts an async coroutine on the default thread
     */
    fun <T> bgTaskAsync(
            scope: CoroutineScope = CoroutineScope(Job()),
             block: suspend CoroutineScope.() -> T
    ): Deferred<T> = startTaskAsync(scope, bg, block)

    /**
     * Starts a coroutine Job that can be made of multiple tasks
     * @param parentScope the parent scope of this job. Facilitates structured concurrency by preserving
     * the execution (and cancellation) context of the job, and propagting exceptions correctly
     * throughout the parent-child hierarchy of the coroutines.
     * @param coroutineContext provides the dispatcher to use as the coroutine's execute context
     */
    fun startJob(
            parentScope: CoroutineScope,
            coroutineContext: CoroutineContext,
            block: suspend CoroutineScope.() -> Unit
    ) {
        parentScope.launch(coroutineContext) {
            supervisorScope {
                block()
            }
        }
    }

    /**
     * Starts sequentially executed coroutine asynchrounous tasks that suspend the parent job/task
     * until it’s done and returns a result
     * @param coroutineContext provides the dispatcher to use as the coroutine's execute context
     * @param block the coroutine body, in its context
     */
    suspend fun <T> startTask(
            coroutineContext: CoroutineContext,
            block: suspend CoroutineScope.() -> T
    ): T = withContext(coroutineContext) {
        return@withContext block()
    }

    /**
     * Starts an asynchronous task that can be executed in parallel amongst others.  We await their result
     * through their Deferred return type.
     * @param parentScope the parent scope of this job. Facilitates structured concurrency by preserving
     * the execution (and cancellation) context of the job, and propagting exceptions correctly
     * throughout the parent-child hierarchy of the coroutines.
     * @param coroutineContext provides the dispatcher to use as the coroutine's execute context
     * @param block the coroutine body, in its context
     */
    fun <T> startTaskAsync(
            parentScope: CoroutineScope,
            coroutineContext: CoroutineContext,
            block: suspend CoroutineScope.() -> T
    ): Deferred<T> = parentScope.async(coroutineContext) {
        return@async supervisorScope {
            return@supervisorScope block()
        }
    }

}


