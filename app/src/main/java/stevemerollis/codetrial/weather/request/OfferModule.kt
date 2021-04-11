package stevemerollis.codetrial.weather.request

import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.request.access.GetOfferUseCase
import stevemerollis.codetrial.weather.request.access.GetOfferUseCaseImpl
import stevemerollis.codetrial.weather.request.quote.QuoteRepository
import stevemerollis.codetrial.weather.request.quote.QuoteRepositoryImpl
import stevemerollis.codetrial.weather.lifetime.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Singleton

@Module
@InstallIn(PurchaseComponent::class)
object OfferModule {

    @Provides 
    fun provideGetQuoteUseCase(getOffer: GetOfferUseCaseImpl): GetOfferUseCase = getOffer

    @Provides 
    fun provideQuoteRepository(networkHelper: NetworkHelper): QuoteRepository {
        return QuoteRepositoryImpl(networkHelper)
    }

}