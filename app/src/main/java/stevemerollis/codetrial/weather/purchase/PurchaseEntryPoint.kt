package stevemerollis.codetrial.weather.lifetime

import dagger.hilt.EntryPoint
import dagger.hilt.InstallIn

@EntryPoint
@InstallIn(PurchaseComponent::class)
interface PurchaseEntryPoint {
    fun getPurchaseModel(): PurchaseModel
}