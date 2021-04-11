package stevemerollis.codetrial.weather

import stevemerollis.codetrial.weather.network.converter.BooleanAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.junit.jupiter.api.*
import org.junit.jupiter.api.TestInstance.Lifecycle
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory


@TestInstance(Lifecycle.PER_CLASS)
abstract class BaseTest {

    var _isInit: Boolean = false
    val isInit: Boolean by lazy { _isInit }
    val nonEmptyString: String = "Some non-empty string value"
    val defaultScope: CoroutineScope = CoroutineScope(Job() + Dispatchers.Unconfined)

    val mocks: List<Any> = emptyList<Any>()

    fun provideClientByLazy() : dagger.Lazy<OkHttpClient> {
        return dagger.Lazy<OkHttpClient> {
            OkHttpClient.Builder().apply {
                HttpLoggingInterceptor().apply {
                    level = HttpLoggingInterceptor.Level.BODY
                    addInterceptor(this)
                }
            }.build()
        }
    }

    fun getRestBuilder() : dagger.Lazy<Retrofit> = dagger.Lazy<Retrofit> {
        val moshi = Moshi.Builder().add(BooleanAdapter()).add(KotlinJsonAdapterFactory()).build()
        val client: OkHttpClient = provideClientByLazy().get()
        return@Lazy Retrofit.Builder()
                    .callFactory(client)
                    .baseUrl("http://10.0.0.1:3000")
                    .addConverterFactory(MoshiConverterFactory.create(moshi))
                    .build()
    }
}


/**
    @BeforeClass
    fun beforeClass() {
        every { dispatchers.Default } returns Dispatchers.Unconfined
        every { dispatchers.Main } returns Dispatchers.Unconfined
        every { dispatchers.IO } returns Dispatchers.Unconfined
        }
        protected lateinit var calibration: VehicleCalibration
        protected lateinit var networkHelper: INetworkHelper
        protected lateinit var papiApi: IPapiApi
        protected val scheduler = TestScheduler()
        protected val app = ApplicationProvider.getApplicationContext<Application>()

        protected fun readResFile(file: String): String {
        val stream = app.classLoader.getResource(file).openStream()

        return stream.bufferedReader().use(BufferedReader::readText)
    }


    /**
     * Offers a hook into the base test class mock initialization and teardown
    */
    open val mocks: Mox<Any> = Mox()

    operator fun <T> getValue(thisRef: Mox<T>, property: KProperty<*>, value: Mox<T>): Boolean where T: Any {
    value.forEach {
    mockkClass(it!!::class)
    }
    return true
    }

    operator fun <T> setValue(thisRef: Mox<T>, property: KProperty<*>, value: Mox<T>): Boolean where T: Any {
    value.forEach {
    mockkClass(it!!::class)
    }
    return true
    }
    inline fun <reified T> mox(block: List<T>.() -> Unit): Mox<T> where T: Any {
    val mutCol: MutableCollection<T> = Collections.checkedCollection(mutableListOf(), T::class.java)
    mocks.addAll(listOf<T>().apply(block).toCollection(mutCol) as MutableCollection<Any>)
    mocks.forEach { mockkClass(it!!::class) }
    return mocks as Mox<T>
    }
 */
