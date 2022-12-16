package jp.co.world.common.network.config

import androidx.annotation.Keep
import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody


@Keep
data class BaseResponsex(
    val code: Int,  //响应码
    val data: Any?, //响应数据内容
    val message: String? //响应数据的结果描述
)

class ResponseInterceptor : Interceptor {

    companion object {
        private val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .create()
        private val mapType = object : TypeToken<Map<String, Any>>() {}.type
    }

    override fun intercept(chain: Interceptor.Chain): Response {

        val response: Response = chain.proceed(chain.request())

        // // if(response.isSuccessful){
        LogUtils.w("resp test")

        val newResponse = response.newBuilder()
        var contentType = response.header("Content-Type")
        if (contentType == null || contentType.length == 0) contentType = "application/json"

        // val mediaType = response.body!!.contentType()

        var responseString = response.body!!.string()

        if(response.request.url.host.indexOf("worldfcl-test.silveregg.net") >= 0){
            responseString  = responseString.substring("callback(".length, responseString.length-2)
//            LogUtils.w("silver rsp", responseString)
        }

        var bbx = BaseResponsex(200, responseString, "message")
        val resBody = Gson().toJson(bbx)



        // val newRspString = "{code: 200, data: aaa, message: jjjj}"

        // val mediaType = contentType.toMediaTypeOrNull()
        val bb =  newResponse.body(ResponseBody.create(response.body!!.contentType(), resBody)).build()

        val infoPrint = if(responseString.length > 101) {responseString.substring(0, 100)} else responseString
        LogUtils.w("new rsp", infoPrint)//responseString.length, responseString, bb)
        return bb
        // // }
        // //
        // return response;
    }
}