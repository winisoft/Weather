package stevemerollis.codetrial.weather.app

import android.content.Context
import android.content.SharedPreferences
import android.content.res.AssetManager
import android.content.res.Resources
import android.net.ConnectivityManager
import androidx.datastore.core.DataStore
import androidx.datastore.core.DataStoreFactory
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import androidx.datastore.preferences.preferencesDataStoreFile
import androidx.room.Room
import com.github.matteobattilana.weather.WeatherView
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dispatch.core.IOCoroutineScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.api.options.UnitsOfMeasure
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepositoryImpl
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.persist.AppDatabase
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.settings.app.Prefs
import stevemerollis.codetrial.weather.util.AppIdImpl
import stevemerollis.codetrial.weather.util.AppIdUtil
import java.io.DataInputStream
import java.io.File
import kotlin.properties.ReadOnlyProperty


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

//    @Provides
//    fun provideWeatherView(
//        @ApplicationContext context: Context
//    ): WeatherView = WeatherView(context, null)

    @Provides
    fun getRoom(@ApplicationContext context: Context)
    : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "weather-db").build()

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

    @Provides
    fun providePrefsStore(@ApplicationContext context: Context, scope: IOCoroutineScope)
    : DataStore<Preferences>
        = PreferenceDataStoreFactory.create(scope = scope, produceFile = {
            File("${context.filesDir}/datastore/weather_prefs.pb")
        })

    @Provides
    fun providePreferenceManager(prefsStore: DataStore<Preferences>)
    : PreferenceManager = PreferenceManager(prefsStore)

    @Provides
    fun bindPrefsStore(prefsManager: PreferenceManager): Prefs = prefsManager

    @ExperimentalCoroutinesApi
    @OptIn(FlowPreview::class)
    @Provides
    fun provideCurrentlyRepository(netHelp: NetworkHelper)
            : CurrentlyRepository =
        CurrentlyRepositoryImpl(netHelp)
}