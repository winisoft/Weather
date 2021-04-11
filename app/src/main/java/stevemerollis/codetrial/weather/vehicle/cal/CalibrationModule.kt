package stevemerollis.codetrial.weather.vehicle.cal

import android.content.Context
import stevemerollis.codetrial.weather.vehicle.cal.brand.BrandUtil
import stevemerollis.codetrial.weather.vehicle.cal.brand.BrandUtilImpl
import stevemerollis.codetrial.weather.vehicle.cal.urls.PapiUrl
import stevemerollis.codetrial.weather.vehicle.cal.urls.PapiUrlImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped
import dagger.hilt.components.SingletonComponent
import dagger.multibindings.IntKey
import dagger.multibindings.IntoMap
import okhttp3.Cache.Companion.key
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CalibrationModule {

    @Provides 
    fun provideCal(): CalibrationManager = CalibrationManager()
    class CalibrationManager {
        constructor(context: Context): this() {}
        constructor()
        fun getString(name: String, default: String): String {
            return "https://lab1.api.gm.com:20445"
        }

        fun getEnumeration(gmBrand: Any, i: Int): Int {
            return 3
        }

        @Suppress("ClassName")
        class GIS500_UI_ENUM {
            @Suppress("EnumEntryName")
            enum class GM_Brand {
                GM_Brand_None, GM_Brand_Chevrolet, GM_Brand_GMC;
            }

        }

        class CalId {
            companion object {
                const val GMBrand: String = ""
                const val BACKOFFICE_SERVER_NAME: String = ""
            }
        }


    }

    @Provides @IntoMap @IntKey(0) 
    fun providePapiUrl(cal: CalibrationManager): PapiUrl = PapiUrlImpl(cal)

    @Provides 
    fun providePapiUrlMapping(
        availableClients: Map<Int, @JvmSuppressWildcards PapiUrl>
    ): PapiUrl = availableClients
            .maxByOrNull { it.key }
            ?.value ?: throw Exception("No PapiUrl was provided as entry")

    @Provides 
    fun provideBrandUtil(cal: CalibrationManager): BrandUtil = BrandUtilImpl(cal)

    @Provides @IntoMap @IntKey(0) 
    fun provideBrandUtilMapping(
            availabileClients: Map<Int, @JvmSuppressWildcards BrandUtil>
    ): BrandUtil = availabileClients
            .maxByOrNull { it.key }
            ?.value ?: throw Exception("No BrandUtil was provided as entry")
}