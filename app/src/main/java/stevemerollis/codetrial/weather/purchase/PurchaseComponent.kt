package stevemerollis.codetrial.weather.lifetime

import dagger.hilt.DefineComponent
import dagger.hilt.components.SingletonComponent

@PurchaseScoped
@DefineComponent(
    parent = SingletonComponent::class
) interface PurchaseComponent {

    @DefineComponent.Builder
    interface Factory {
        fun create(): PurchaseComponent
    }

}