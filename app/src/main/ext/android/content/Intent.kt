package android.content



val Intent?.logText: String
    get() = this?.run { toUri(Intent.URI_INTENT_SCHEME) } ?: ""
