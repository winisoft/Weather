package stevemerollis.codetrial.weather.paymethods

import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.network.state.NetStateUtil
import stevemerollis.codetrial.weather.paymethods.access.PayMethodRepository
import stevemerollis.codetrial.weather.paymethods.access.PayMethodRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(ActivityRetainedComponent::class)
class PayMethodsModule {

    @Provides

    fun providePayMethodRepository(helper: NetworkHelper, netStateUtil: NetStateUtil)
    : PayMethodRepository = PayMethodRepositoryImpl(helper, netStateUtil)
}