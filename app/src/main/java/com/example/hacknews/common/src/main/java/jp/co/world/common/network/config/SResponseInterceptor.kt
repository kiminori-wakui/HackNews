package jp.co.world.common.network.config

import com.blankj.utilcode.util.LogUtils
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import jp.co.world.common.utils.SILVER_DEV
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody


class SResponseInterceptor : Interceptor {

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

        if(response.request.url.toString().indexOf(SILVER_DEV) >= 0){

            val regex = Regex("""callback\((.*)\)""")
            val match = regex.find(responseString)

            if(match?.groups?.size ?: 0 <= 1){
                responseString = "{ rcd: 500 }"
            }else {
                responseString = match?.groups?.get(1)?.value ?: ""
            }
        }

        var bbx = BaseResponsex(200, "XXXX-XXXX", "message")
        val resBodyRaw = Gson().toJson(bbx)
        LogUtils.w("silver str", resBodyRaw)
        val resBody = resBodyRaw.replace("\"XXXX-XXXX\"", responseString)
        LogUtils.w("silver str2", resBody)

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