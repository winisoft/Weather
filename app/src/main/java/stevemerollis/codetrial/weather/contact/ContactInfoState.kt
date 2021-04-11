package stevemerollis.codetrial.weather.contact

import stevemerollis.codetrial.weather.async.Model
import stevemerollis.codetrial.weather.contact.domain.ContactMethod


sealed class ContactInfoState {

    class DisplayContactInfos(val contactInfos: List<ContactMethod>): ContactInfoState()

    class Error(val errorModel: Model.Error): ContactInfoState()
}