package stevemerollis.codetrial.weather.request.terms.model

import android.content.res.AssetManager
import android.content.res.AssetManager.ACCESS_STREAMING
import android.content.res.Resources
import android.text.Spanned
import androidx.core.text.HtmlCompat
import stevemerollis.codetrial.weather.R
import stevemerollis.codetrial.weather.async.AsyncResult
import stevemerollis.codetrial.weather.async.coroutine.CoroutineDsl
import stevemerollis.codetrial.weather.request.terms.Terms
import stevemerollis.codetrial.weather.request.terms.TermsClient
import stevemerollis.codetrial.weather.request.terms.TermsClient.*
import kotlinx.coroutines.*
import java.io.BufferedReader
import javax.inject.Inject


class TermsRepositoryImpl
@Inject
constructor(
        private val assetManager: AssetManager,
        private val resources: Resources
): TermsRepository {

    override suspend fun termsAsync(scope: CoroutineScope, termsClient: TermsClient): Deferred<AsyncResult<Terms>> {
        return when (termsClient) {
            ATT -> Terms(
                    summary = resources.getString(R.string.default_terms),
                    extended = readAssets(scope, "terms/att_terms.html"),
                    isAcceptRequired = true,
                    termsClient = ATT,
            )
            else -> Terms(
                    summary = resources.getString(R.string.default_terms),
                    extended = readAssets(scope, "terms/att_terms.html"),
                    isAcceptRequired = true,
                    termsClient = ATT,
            )
        }.let {
            scope.async { AsyncResult.Success(it) as AsyncResult<Terms> }
        }
    }

    //supressed reason: compiler is not capable yet of determining the context in this case
    @Suppress("BlockingMethodInNonBlockingContext")
    private suspend fun readAssets(scope: CoroutineScope, fileName: String): Spanned =
            withContext(scope.coroutineContext + CoroutineDsl.io){
                assetManager.run {
                    open(fileName, ACCESS_STREAMING)
                            .bufferedReader()
                            .use(BufferedReader::readText)
                            .let { HtmlCompat.fromHtml(it, HtmlCompat.FROM_HTML_MODE_LEGACY) }
                }
            }
}

