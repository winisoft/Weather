package stevemerollis.codetrial.weather.intention

interface IntentionFactory<E>{
    suspend fun process(viewEvent: E)
}