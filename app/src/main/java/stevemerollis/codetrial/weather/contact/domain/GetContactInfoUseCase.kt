package stevemerollis.codetrial.weather.contact.domain

import stevemerollis.codetrial.weather.async.Model
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow

interface GetContactInfoUseCase {

    suspend operator fun invoke(scope: CoroutineScope): Flow<Model<List<ContactMethod>>>

}