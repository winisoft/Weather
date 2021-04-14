package stevemerollis.codetrial.weather.app

import android.content.Context
import android.net.ConnectivityManager
import androidx.room.Room
import com.github.matteobattilana.weather.WeatherView
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineScope
import stevemerollis.codetrial.weather.persist.AppDatabase
import stevemerollis.codetrial.weather.util.lo
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideWeatherView(
        @ApplicationContext context: Context
    ): WeatherView = WeatherView(context, null)

    @Provides
    fun bindConnectivityManager(@ApplicationContext context: Context)
    : ConnectivityManager =
        with(context, { getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager })

    @Provides
    fun getRoom(@ApplicationContext context: Context)
    : AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "weather-db").build()

    @Provides
    fun provideAppCoroScope()
    : CoroutineScope =
        AppCoroScope()
//    @Provides
//    fun provideAssetManager(@ApplicationContext context: Context): AssetManager =
//            context.assets
//
//    @Provides
//    fun provideResources(@ApplicationContext context: Context): Resources =
//            context.resources
//
//    @Provides
//    fun provideAccountManager(@ApplicationContext context: Context)
//    : AccountManager = AccountManager.get(context)
//
//    @Provides
//    fun provideContext(@ApplicationContext context: Context) : Context = context
//
//    @Provides
//    fun provideHostState(): HostState = HostState()
}