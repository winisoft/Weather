package stevemerollis.codetrial.weather.validate

import stevemerollis.codetrial.weather.auth.AuthUtil
import stevemerollis.codetrial.weather.network.helper.NetworkHelper
import stevemerollis.codetrial.weather.validate.rules.application.RequestPasscodeUseCase
import stevemerollis.codetrial.weather.validate.rules.application.RequestPasscodeUseCaseImpl
import stevemerollis.codetrial.weather.validate.rules.application.ConfirmPasscodeUseCase
import stevemerollis.codetrial.weather.validate.rules.application.ConfirmPasscodeUseCaseImpl
import stevemerollis.codetrial.weather.validate.rules.business.OtpRepository
import stevemerollis.codetrial.weather.validate.rules.business.OtpRepositoryImpl
import com.gm.info3_new_authmanager.AuthManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ValidateModule {

    @Provides 
    fun provideConfirmPasscodeUseCase(authUtil: AuthUtil, otpRepository: OtpRepository)
    : ConfirmPasscodeUseCase = ConfirmPasscodeUseCaseImpl(authUtil, otpRepository)

    @Provides 
    fun provideOtpRepository(networkHelper: NetworkHelper)
    : OtpRepository = OtpRepositoryImpl(networkHelper)

    @Provides 
    fun provideRequestUseCase(
        authUtil: AuthUtil,
        otpRepository: OtpRepository
    ): RequestPasscodeUseCase = RequestPasscodeUseCaseImpl(authUtil, otpRepository)
}
