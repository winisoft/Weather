package stevemerollis.codetrial.weather.app

import android.content.Context
import android.content.res.AssetManager
import android.content.res.Resources
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.PreferenceDataStoreFactory
import androidx.datastore.preferences.core.Preferences
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dispatch.core.IOCoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.FlowPreview
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepository
import stevemerollis.codetrial.weather.currently.app.CurrentlyRepositoryImpl
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.persist.AppDatabase
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import java.io.File


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun getRoom(@ApplicationContext context: Context)
    : AppDatabase = Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.name).build()

    @Provides
    fun provideAssetManager(@ApplicationContext context: Context): AssetManager = context.assets

    @Provides
    fun provideResources(@ApplicationContext context: Context): Resources = context.resources

    @Provides
    fun providePrefsStore(@ApplicationContext context: Context, scope: IOCoroutineScope)
    : DataStore<Preferences>
        = PreferenceDataStoreFactory.create(scope = scope, produceFile = {
            File("${context.filesDir}/datastore/weather_prefs.pb")
        })

    @Provides
    fun providePreferenceManager(prefsStore: DataStore<Preferences>)
    : PreferenceManager = PreferenceManager(prefsStore)

    @ExperimentalCoroutinesApi
    @OptIn(FlowPreview::class)
    @Provides
    fun provideCurrentlyRepository(netHelp: NetworkHelper)
            : CurrentlyRepository =
        CurrentlyRepositoryImpl(netHelp)
}