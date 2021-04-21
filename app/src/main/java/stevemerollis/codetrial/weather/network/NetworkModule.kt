package stevemerollis.codetrial.weather.network

import android.content.Context
import android.net.ConnectivityManager
import stevemerollis.codetrial.weather.network.state.NetStateUtil
import stevemerollis.codetrial.weather.network.converter.BooleanAdapter
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.network.helper.NetworkHelperImpl
import stevemerollis.codetrial.weather.api.OpenWeatherApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import dispatch.core.DispatcherProvider
import okhttp3.*
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import stevemerollis.codetrial.weather.app.AppCoroScope
import stevemerollis.codetrial.weather.network.state.SimpleNetStateUtil
import javax.inject.Named
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides 
    fun provideMoshi(): Moshi = Moshi
        .Builder().apply {
            add(BooleanAdapter())
            add(KotlinJsonAdapterFactory())
        }.build()

    @Provides 
    fun provideMoshiConverter(moshi: Moshi): MoshiConverterFactory = MoshiConverterFactory.create(moshi)

    @Provides @Singleton
    fun provideNetStateUtil(
        availableClients: Map<Int, @JvmSuppressWildcards NetStateUtil>
    ): NetStateUtil {
        return availableClients.maxByOrNull { it.key }?.value ?: throw Exception("None were provided as entries")
    }

    @Provides
    @IntoMap
    @IntKey(0)
    @Singleton
    fun netStateUtil(
        @ApplicationContext context: Context,
        coroutineScope: AppCoroScope
    ): NetStateUtil =
        SimpleNetStateUtil(context, coroutineScope)


    @Provides @Singleton
    fun provideOkHttp(
        availableClients: Map<Int, @JvmSuppressWildcards OkHttpClient>
    ): OkHttpClient {
        return availableClients.maxByOrNull { it.key }?.value ?: throw Exception("None were provided as entries")
    }

    @Provides
    @IntoMap
    @IntKey(0)
    @Singleton
    fun okHttpClient()
    : OkHttpClient =
        OkHttpClient.Builder().build()

    @Provides @Singleton
    fun provideRetrofitMapping(
        availableClients: Map<Int, @JvmSuppressWildcards Retrofit>
    ): Retrofit {
        return availableClients.maxByOrNull { it.key }?.value ?: throw Exception("None were provided as entries")
    }

    @Provides
    @IntoMap
    @IntKey(0)
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        moshi: MoshiConverterFactory,
    ): Retrofit = Retrofit.Builder().apply {
        client(okHttpClient)
        baseUrl("https://openweathermap.org/api/")
        addConverterFactory(moshi)
    }.build()


    @Provides

    fun providePapMapping(
            availableClients: Map<Int, @JvmSuppressWildcards OpenWeatherApi>
    ): OpenWeatherApi {
        return availableClients.maxByOrNull { it.key }?.value ?: throw Exception("None were provided as entries")
    }

    @Provides
    @IntoMap
    @IntKey(0)
    fun providePapi(retrofit: Retrofit): OpenWeatherApi {
        return retrofit.create(OpenWeatherApi::class.java)
    }

    @Provides
    fun provideNetworkHelperMapping(
            availableClients: Map<Int, @JvmSuppressWildcards NetworkHelper>
    ): NetworkHelper {
        return availableClients.maxByOrNull { it.key }?.value ?: throw Exception("None were provided as entries")
    }

    @Provides
    @IntoMap
    @IntKey(0)
    fun provideNetHelper(
        dispatcher: DispatcherProvider,
        openWeatherApi: OpenWeatherApi,
        netStateUtil: NetStateUtil
    ): NetworkHelper = NetworkHelperImpl(dispatcher, openWeatherApi, netStateUtil)
}