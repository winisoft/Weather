package stevemerollis.codetrial.weather.contact.ui

import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.contact.domain.ContactMethod


sealed class ContactViewState {

    class ShowContacts(val contactInfos: List<ContactMethod>): ContactViewState()

    class Error(val errorModel: Model.Error): ContactViewState()
}