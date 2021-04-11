package stevemerollis.codetrial.weather.async.coroutine

import stevemerollis.codetrial.weather.BaseTest
import kotlinx.coroutines.*
import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.coroutines.CoroutineContext

@ExperimentalCoroutinesApi
@ObsoleteCoroutinesApi
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CoroutineDslTest : BaseTest()  {

//    @ObsoleteCoroutinesApi
//    val mainThreadSurrogate: ExecutorCoroutineDispatcher = newSingleThreadContext("UI thread")
//    @ExperimentalCoroutinesApi
//    private val scope: TestCoroutineScope = TestCoroutineScope()
//    private val context: CoroutineContext = scope.coroutineContext
//    private val sut: CoroutineDsl = CoroutineDsl
//
//    @BeforeAll
//    fun setup() {
//        Dispatchers.setMain(mainThreadSurrogate)
//    }
//
//    @AfterAll
//    fun cleanUp() {
//        scope.cleanupTestCoroutines()
//    }
//
//    @Nested
//    @DisplayName("Given that each dsl method represents coroutine execution")
//    inner class BasicExecution {
//
//        @Nested
//        @DisplayName("When each function is called to begin")
//        inner class GeneralizedCases {
//
//            val block: CoroutineScope.() -> Unit = {}
//            val answerJob: suspend MockKAnswerScope<Job, Job>.(Call) -> Job = { Job() }
//            lateinit var newContext: CoroutineContext
//            var scoper: MockKMatcherScope.() -> Unit = { }
//
//            @BeforeEach
//            fun setup() {
//                init()
//                mockkStatic(CoroutineDsl::class)
//                //every { CoroutineDsl::bgJob.invoke(scope, block) } answers { answerJob }
//            }
//
//            @Test
//            @DisplayName("Then it should start coroutine execution")
//            fun testFoo(): Unit = scope.runBlockingTest {
//                sut.bgJob(scope, {})
//                coVerifyAll {
//                    CoroutineDsl.startJob(scope, scope.coroutineContext, {})
//                    scope.launch(scope.coroutineContext, CoroutineStart.DEFAULT, {})
//                    newCoroutineContext(scope.coroutineContext).also { newContext = it }
//                }
//            }
//        }
//    }
}
