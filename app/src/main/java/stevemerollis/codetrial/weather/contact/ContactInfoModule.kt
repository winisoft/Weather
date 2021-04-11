package stevemerollis.codetrial.weather.contact

import stevemerollis.codetrial.weather.auth.AuthUtil
import stevemerollis.codetrial.weather.contact.data.access.ContactInfoRepositoryImpl
import stevemerollis.codetrial.weather.contact.data.access.ContactInfoRepository
import stevemerollis.codetrial.weather.contact.domain.GetContactInfoUseCase
import stevemerollis.codetrial.weather.contact.domain.GetContactInfoUseCaseImpl
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ContactInfoModule {
    
    @ExperimentalCoroutinesApi
    @Provides 
    fun provideGetContactInfoUseCase(
            authUtil: AuthUtil,
            contactInfoRepository: ContactInfoRepository
    ): GetContactInfoUseCase = GetContactInfoUseCaseImpl(authUtil, contactInfoRepository)

    @Provides 
    fun provideRepository(
        networkHelper: NetworkHelper
    ): ContactInfoRepository =
            ContactInfoRepositoryImpl(networkHelper)
}