package stevemerollis.codetrial.weather.vehicle

import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.vehicle.cal.CalibrationModule

import stevemerollis.codetrial.weather.vehicle.cal.CalibrationModule.CalibrationManager.GIS500_UI_ENUM

sealed class Brand {

    abstract val theme: Int
    abstract val supportNumber: String
    abstract val calGisEnumOrdinal: GIS500_UI_ENUM.GM_Brand

    object Chevrolet: Brand() {
        override fun toString(): String = "Chevrolet"
        override val theme: Int = R.style.AppTheme_Chevy
        override val supportNumber: String = "8888813192"
        override val calGisEnumOrdinal: GIS500_UI_ENUM.GM_Brand
            get() = GIS500_UI_ENUM.GM_Brand.GM_Brand_Chevrolet
    }

    object GMC: Brand() {
        override fun toString(): String = "GMC"
        override val theme: Int = R.style.AppTheme_GMC
        override val supportNumber: String = "8888813192"
        override val calGisEnumOrdinal: GIS500_UI_ENUM.GM_Brand
            get() = GIS500_UI_ENUM.GM_Brand.GM_Brand_GMC
    }

    object Unknown: Brand() {
        override val theme: Int = -1
        override val supportNumber: String = ""
        override val calGisEnumOrdinal: GIS500_UI_ENUM.GM_Brand
            get() = GIS500_UI_ENUM.GM_Brand.GM_Brand_None
    }

    companion object {

        fun fromGisEnumOrdinal(ordinal: Int): Brand = when(ordinal) {
            GIS500_UI_ENUM.GM_Brand.GM_Brand_Chevrolet.ordinal -> Chevrolet
            GIS500_UI_ENUM.GM_Brand.GM_Brand_GMC.ordinal -> GMC
            else -> Unknown
        }
    }
}