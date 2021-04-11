@file:Suppress("MemberVisibilityCanBePrivate")
package stevemerollis.codetrial.weather.vehicle.cal.brand

import stevemerollis.codetrial.weather.vehicle.cal.CalibrationModule.CalibrationManager
import stevemerollis.codetrial.weather.vehicle.cal.CalibrationModule.CalibrationManager.CalId
import stevemerollis.codetrial.weather.vehicle.Brand
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject
import javax.inject.Singleton



class BrandUtilImpl
@Inject
constructor(
    val cal: CalibrationManager
): BrandUtil {

    override val brand: Brand = Brand.fromGisEnumOrdinal(
        cal.getEnumeration(CalId.GMBrand, 0)
    ).let { if (it is Brand.Unknown) Brand.Chevrolet else it  }

    companion object {
        const val DEFAULT_BRAND_ORDINAL = 0
    }
}