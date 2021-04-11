package stevemerollis.codetrial.weather.mocknetwork

import okhttp3.Interceptor
import okhttp3.Protocol
import okhttp3.Response
import okhttp3.ResponseBody.Companion.toResponseBody
import org.json.JSONObject


//class MockInterceptor : Interceptor {
//
//    override fun intercept(chain: Interceptor.Chain): Response {
//        val json = JSONObject()
//        json.put("name", "test")
//
//        return Response.Builder()
//            .request(chain.request())
//            .code(mockResponse.responseCode)
//            .protocol(Protocol.HTTP_2)
//            .message(mockResponse.message)
//            .body(mockResponse.body.toByteArray().toResponseBody())
//            .addHeader("content-type", "application/json")
//            .build()
//    }
//
//    companion object {
//        private lateinit var mockResponse: MockResponse
//
//        fun setResponse(response: MockResponse) {
//            mockResponse = response
//        }
//    }
//}