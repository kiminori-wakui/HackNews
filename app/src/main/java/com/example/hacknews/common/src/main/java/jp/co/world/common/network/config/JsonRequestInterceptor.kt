package jp.co.world.common.network.config

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import jp.co.world.common.utils.URLUtils
import okhttp3.*
import java.lang.reflect.Type


class JsonRequestInterceptor : Interceptor {
    companion object {
        private val gson: Gson = GsonBuilder()
        .enableComplexMapKeySerialization()
        .create()
        private val mapType: Type = object : TypeToken<Map<String, Any>>() {}.type
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest: Request = chain.request()

        val attachHeaders: MutableList<Pair<String, String>> = mutableListOf<Pair<String, String>>(
            "Content-Type" to "application/x-www-form-urlencoded; charset=UTF-8",
            // "Accept" to "application/json",
            "X-APP-MODE" to "ap",
            "X-Efs-Device-Div" to "20"
        )


        val urlStr = originRequest.url.toString()
        if(urlStr.contains("wos-chk")){
            attachHeaders.add("Authorization" to
                Credentials.basic(
                    "chk-test-wosusr",
                    "p@48+tgG"))
        }


        // val localToken = MySpUtils.getString(SP_KEY_USER_TOKEN, originRequest.header("token")) ?: ""
        // if (localToken.isNotEmpty()) {
        //    attachHeaders.add("token" to localToken)
        // }

        val requestBody: RequestBody? = originRequest.body

        var newBuilder: Request.Builder = originRequest.newBuilder()
        var httpUrl: HttpUrl? = null

        if(originRequest.method == "GET"){
            val ext = originRequest.url.queryParameter("myextpara")
            if(ext != null){
                val urlBuilder = originRequest.url.newBuilder().removeAllQueryParameters("myextpara")
                val extKVs = URLUtils.getQueriesFromURI(ext)
//                val oldQueryNames = originRequest.url.queryParameterNames
//                for(oldName in oldQueryNames){
//                    if(oldName != "myextpara"){
//
//                    }
//                }
                for(extkey in extKVs.keys){
                    urlBuilder.addQueryParameter(extkey,extKVs[extkey])
                }
                httpUrl = urlBuilder.build()
            }
        }

        // newBuilder.removeHeader("Connection")
        val url = originRequest.url.toString()
        if(!url.contains("actk")) {
            newBuilder.removeHeader("User-Agent")
            newBuilder.removeHeader("Content-Type")
            newBuilder.removeHeader("Accept-Encoding")
        }
            // .cacheControl(CacheControl.FORCE_CACHE)
        attachHeaders.forEach { newBuilder.header(it.first, it.second) }

        if (originRequest.method == "POST" && requestBody != null) {
            newBuilder.post(requestBody)
        } else if (originRequest.method == "GET") {
            if(httpUrl != null){
                newBuilder.url(httpUrl).get()
            }else {
                newBuilder.get()
            }
        }
        return chain.proceed(newBuilder.build())
    }
}