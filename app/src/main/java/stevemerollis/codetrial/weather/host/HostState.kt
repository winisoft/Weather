package stevemerollis.codetrial.weather.host

import android.content.Intent
import stevemerollis.codetrial.weather.contact.domain.CodeRequestBody
import stevemerollis.codetrial.weather.contact.domain.ContactMethod
import stevemerollis.codetrial.weather.request.OfferModel
import dagger.hilt.android.scopes.ActivityRetainedScoped
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


class HostState
@Inject
constructor() {

    var intent: Intent? = null

}