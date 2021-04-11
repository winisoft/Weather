package stevemerollis.codetrial.weather.contact.domain


sealed class ContactMethod() {

    abstract val codeRequestBody: CodeRequestBody

    data class Phone(val value: String, val formatted: String) : ContactMethod() {

        override val codeRequestBody: CodeRequestBody = object: CodeRequestBody("PHONE") {
            val number: String = value
        }
    }

    data class Email(val value: String, val formatted: String) : ContactMethod() {

        override val codeRequestBody: CodeRequestBody = object: CodeRequestBody("EMAIL"){}

    }

    companion object {
        val TAG: String = ContactMethod::class.simpleName!!
    }
}