package stevemerollis.codetrial.weather.lifetime

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn


@Module
@InstallIn(PurchaseComponent::class)
class PurchaseModule {

    @Provides @PurchaseScoped
    fun providePurchaseState(): PurchaseModel  {
        return PurchaseModel()
    }

}