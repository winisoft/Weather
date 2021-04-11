package stevemerollis.codetrial.weather.app

import android.content.Context
import android.net.ConnectivityManager
import dagger.Binds
import dagger.BindsInstance
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import stevemerollis.codetrial.weather.util.lo
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides fun bindContext(@ApplicationContext context: Context): Context = context

    @Provides @Singleton
    fun bindConnectivityManager(@ApplicationContext context: Context)
    : ConnectivityManager =
        with(context, { getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager })

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