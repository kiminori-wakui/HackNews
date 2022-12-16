package jp.co.world.common.network.config

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import okhttp3.*


class XMLRequestInterceptor : Interceptor {

    companion object {
        private val gson = GsonBuilder()
            .enableComplexMapKeySerialization()
            .create()
        private val mapType = object : TypeToken<Map<String, Any>>() {}.type
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()

        var attachHeaders: MutableList<Pair<String, String>> = mutableListOf<Pair<String, String>>(
            "X-APP-MODE" to "ap",
            "Content-type" to "text/xml"
        )

        val urlStr = originRequest.url.toString()
        if(urlStr.indexOf("tsv") > 0){
            attachHeaders = mutableListOf<Pair<String, String>>()
        }

        if(urlStr.contains("wos-chk")){
            attachHeaders.add("Authorization" to
                    Credentials.basic(
                        "chk-test-wosusr",
                        "p@48+tgG"))
        }


        val requestBody: RequestBody? = originRequest.body

        val newBuilder: Request.Builder = originRequest.newBuilder()

        newBuilder.removeHeader("Content-Type")
        newBuilder.removeHeader("Accept-Encoding")
        // .cacheControl(CacheControl.FORCE_CACHE)
        attachHeaders.forEach { newBuilder.header(it.first, it.second) }

        if (originRequest.method == "POST" && requestBody != null) {
            newBuilder.post(requestBody)
        } else if (originRequest.method == "GET") {
            newBuilder.get()
        }


        return chain.proceed(newBuilder.build())
    }

}











