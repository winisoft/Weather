package stevemerollis.codetrial.weather.host

import android.content.Intent

import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject


class HostState
@Inject
constructor() {

    var intent: Intent? = null

}