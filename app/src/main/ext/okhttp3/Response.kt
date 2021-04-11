package okhttp3

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.onEach

fun Flow<Response>.log(): Flow<Response> = onEach {

}