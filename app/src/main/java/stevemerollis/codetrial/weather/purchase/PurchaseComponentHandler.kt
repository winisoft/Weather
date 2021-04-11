package stevemerollis.codetrial.weather.lifetime

import android.content.Intent
import dagger.hilt.internal.GeneratedComponentManager
import javax.inject.Inject
import javax.inject.Singleton


class PurchaseComponentHandler
constructor(
    private val componentFactory: PurchaseComponent.Factory
) : GeneratedComponentManager<PurchaseComponent> {

    var purchaseComponent: PurchaseComponent? = null
        private set

    fun receiveArgsIntent(intent: Intent?) {
        purchaseComponent = componentFactory.create(intent)
    }

    fun broadcastCompleteIntent() {
        purchaseComponent = null
    }

    override fun generatedComponent(): PurchaseComponent = purchaseComponent!!
}