package stevemerollis.codetrial.weather.request.access

import android.content.Intent
import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.request.OfferModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GetOfferUseCase {

    suspend operator fun invoke(scope: CoroutineScope, intent: Intent?): Flow<Model<OfferModel>>
}