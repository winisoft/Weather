package stevemerollis.codetrial.weather.app

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.room.Room
import com.github.matteobattilana.weather.WeatherView
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import stevemerollis.codetrial.weather.persist.AppDatabase
//import stevemerollis.codetrial.weather.settings.app.PreferenceManager
//import stevemerollis.codetrial.weather.settings.app.PreferenceSerializer
import stevemerollis.codetrial.weather.util.AppIdImpl
import stevemerollis.codetrial.weather.util.AppIdUtil
import java.io.File


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideWeatherView(
        @ApplicationContext context: Context
    ): WeatherView = WeatherView(context, null)

    @Provides
    fun getRoom(@ApplicationContext context: Context)
    : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "weather-db").build()

    @Provides
    fun provideAppCoroScope()
    : CoroutineScope =
        AppCoroScope()

    @Provides
    fun provideAssetManager(@ApplicationContext context: Context)
    : AssetManager =
            context.assets

    @Provides
    fun provideResources(@ApplicationContext context: Context)
    : Resources =
            context.resources

    @Provides
    fun provideAppIdUtil(implementation: AppIdImpl): AppIdUtil = implementation

//    @Provides
//    fun provideDataStore()
//    : DataStore<WeatherPreferences> =
//        DataStoreFactory.create(
//            serializer = PreferenceSerializer,
//            produceFile = {
//                return@create File(
//                    "src/main/proto/${PreferenceManager.DATA_STORE_FILE_NAME}"
//                )
//            }
//        )
}