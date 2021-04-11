package java.io

import android.text.Spanned
import androidx.core.text.toSpannable
import androidx.core.text.toSpanned
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.asFlow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.flowOn
import okio.ByteString.Companion.readByteString


fun InputStream.flowOfStrings(): Flow<String> = bufferedReader()
        .run { use { it.lineSequence().asFlow().flowOn(Dispatchers.IO) } }

fun InputStream.readSpanned(): String = bufferedReader().run {
        available()
        ByteArray(1024).let {
                read(it)
                close()
                String(it)
        }


}