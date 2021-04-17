package android.widget

import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import androidx.annotation.CheckResult
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.take
import stevemerollis.codetrial.weather.util.lo.logD
import kotlin.coroutines.EmptyCoroutineContext

@ExperimentalCoroutinesApi
fun EditText.firstChange(): Flow<Unit> {
    return callbackFlow<Unit> {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                offer(Unit)
            }
        }.also { addTextChangedListener(it) }

        awaitClose {
            Dispatchers.Main.dispatch(EmptyCoroutineContext) {
                removeTextChangedListener(listener)
                logD { "EditText: removeTextChangedListener $listener ${this@firstChange}" }
            }
        }
    }.take(1)
}

@ExperimentalCoroutinesApi
@CheckResult
fun EditText.textChanges(): Flow<CharSequence?> {
    return callbackFlow<CharSequence?> {
        val listener = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) = Unit
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) = Unit
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                offer(s)
            }
        }
        addTextChangedListener(listener)
        awaitClose { removeTextChangedListener(listener) }
    }.onStart { emit(text) }
}