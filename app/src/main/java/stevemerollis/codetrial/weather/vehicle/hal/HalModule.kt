package stevemerollis.codetrial.weather.vehicle.hal

import stevemerollis.codetrial.weather.vehicle.hal.vin.VinUtil
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton
import stevemerollis.codetrial.weather.vehicle.hal.Car.CarPropertyManager
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object HalModule {

    @Provides 
    fun carPropertyManager() = CarPropertyManager()


    @Provides
    
    fun provideVinUtilMapping(availableClients: Map<Int, @JvmSuppressWildcards VinUtil>): VinUtil =
            availableClients.maxByOrNull { it.key }?.value ?: throw Exception("No VinUtil was provided as entry")

}