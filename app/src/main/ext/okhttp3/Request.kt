package okhttp3

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

val Request.logText: String
    get() = ""

fun Flow<Request>.log(): Flow<Request> = onEach {


}