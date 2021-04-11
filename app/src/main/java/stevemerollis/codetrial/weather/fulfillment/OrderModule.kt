package stevemerollis.codetrial.weather.fulfillment

import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.fulfillment.access.app.OrderUseCase
import stevemerollis.codetrial.weather.fulfillment.access.app.OrderUseCaseImpl
import stevemerollis.codetrial.weather.fulfillment.access.business.OrderRepository
import stevemerollis.codetrial.weather.fulfillment.access.business.OrderRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object OrderModule {

    @Provides 
    fun provideOrderUseCase(orderRepository: OrderRepository): OrderUseCase = OrderUseCaseImpl(orderRepository)

    @Provides 
    fun provideOrderRepository(networkHelper: NetworkHelper): OrderRepository = OrderRepositoryImpl(networkHelper)

}