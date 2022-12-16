package jp.co.world.common.utils

import androidx.core.net.toUri
import okhttp3.Interceptor
import okhttp3.Response


class SHostInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()
        val oldHost = originRequest.url.host
        val oldUrlStr = originRequest.url.toString()


        var newHost = getBaseHost().toUri().host ?: oldHost
        var newUrlStr = if (oldUrlStr.indexOf("staff-start") < 0) {
            if (newHost == oldHost) {
                oldUrlStr
            } else oldUrlStr.replace(oldHost, newHost)
        } else oldUrlStr

        if(oldUrlStr.indexOf("test") >= 0 && oldUrlStr.indexOf("staff-start") < 0){
            newHost = "192.168.4.8:8000"
            newUrlStr = oldUrlStr.replace(oldHost, newHost)
            newUrlStr = newUrlStr.replace("https", "http")
        }



        val newBuilder = originRequest.newBuilder()
        newBuilder.url(newUrlStr)
        return chain.proceed(newBuilder.build())
    }

}