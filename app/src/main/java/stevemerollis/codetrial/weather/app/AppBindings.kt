package stevemerollis.codetrial.weather.app

import androidx.fragment.app.FragmentFactory
import androidx.navigation.fragment.NavHostFragment
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import stevemerollis.codetrial.weather.settings.app.PreferenceManager
import stevemerollis.codetrial.weather.settings.app.Prefs
import stevemerollis.codetrial.weather.api.id.AppIdImpl
import stevemerollis.codetrial.weather.api.id.AppIdUtil
import stevemerollis.codetrial.weather.conditions.entity.ConditionsHelper
import stevemerollis.codetrial.weather.conditions.entity.ConditionsHelperImpl
import stevemerollis.codetrial.weather.main.MainFragmentFactory

@Module
@InstallIn(SingletonComponent::class)
abstract class AppBindings {

    @OptIn(ExperimentalCoroutinesApi::class)
    @Binds
    abstract fun provideFragmentFactory(ff: MainFragmentFactory): FragmentFactory

    @Binds
    abstract fun provideAppIdUtil(implementation: AppIdImpl): AppIdUtil

    @Binds
    abstract fun bindPrefsStore(prefsManager: PreferenceManager): Prefs

    @Binds
    abstract fun bindConditionHelper(conditionHelp: ConditionsHelperImpl): ConditionsHelper
}