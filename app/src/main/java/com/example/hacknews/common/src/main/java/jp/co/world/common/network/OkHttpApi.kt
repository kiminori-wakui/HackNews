package jp.co.world.common.network

import androidx.collection.SimpleArrayMap
import jp.co.world.common.network.config.XMLRequestInterceptor
import jp.co.world.common.network.config.LocalCookieJar
import jp.co.world.common.network.config.RetryInterceptor
import jp.co.world.common.network.support.IHttpCallback
import com.google.gson.Gson
//import com.opencsv.bean.CsvToBeanBuilder
import com.zsk.common.network.config.KtHttpLogInterceptor
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.suspendCancellableCoroutine
import com.example.hacknews.common.src.main.res.config.SP_KEY_CUSTOM_EFS_VID
import com.example.hacknews.common.src.main.res.config.SP_KEY_LOGINED
import com.example.hacknews.common.src.main.res.config.SP_KEY_SSI
import com.example.hacknews.common.src.main.res.config.SP_KEY_USER_TOKEN
import jp.co.world.common.utils.*
import okhttp3.*
import okhttp3.HttpUrl.Companion.toHttpUrl
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import java.io.*
import java.util.concurrent.TimeUnit
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException

class OkHttpApi private constructor() : HttpApi {

    var maxRetry = 0

    public val callMap = SimpleArrayMap<Any, Call>()

    public val jar = LocalCookieJar()

    //okHttpClient
    private val defaultClient = OkHttpClient.Builder()
        .callTimeout(10, TimeUnit.SECONDS)
        .connectTimeout(10, TimeUnit.SECONDS)
        .readTimeout(10, TimeUnit.SECONDS)
        .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
        .cache(Cache(File("sdcard/cache", "okhttp"), 1024))
//        .cookieJar(CookieJar.NO_COOKIES)
        .cookieJar(jar)
        .addNetworkInterceptor(XMLRequestInterceptor())
        .addNetworkInterceptor(KtHttpLogInterceptor {
            logLevel(KtHttpLogInterceptor.LogLevel.BODY)
        })
        .addNetworkInterceptor(RetryInterceptor(maxRetry))
//        .hostnameVerifier(HostnameVerifier { p0, p1 -> true })
//        .sslSocketFactory(sslSocketFactory = null,trustManager = null)
        .build()

    public var mClient = defaultClient

    fun getClient() = mClient



    fun initConfig(client: OkHttpClient) {
        this.mClient = client
    }

    companion object {
        @Volatile
        private var api: OkHttpApi? = null

        @Synchronized
        fun getInstance(): OkHttpApi {
            return api ?: OkHttpApi().also { api = it }
        }
    }


    override fun get(params: Map<String, Any>, urlStr: String, callback: IHttpCallback) {
        val urlBuilder = urlStr.toHttpUrl().newBuilder()
        params.forEach { entry ->
            urlBuilder.addEncodedQueryParameter(entry.key, entry.value.toString())
        }

        val request = Request.Builder()
            .get()
            .tag(params)
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        val newCall = mClient.newCall(request)

        callMap.put(request.tag(), newCall)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response.body?.string())
            }

        })
    }

    override fun post(body: Any, urlStr: String, callback: IHttpCallback) {

        val reqBody = Gson().toJson(body).toRequestBody("application/json".toMediaType())

        val request = Request.Builder()
            .post(reqBody)
            .url(urlStr)
            .tag(body)
            .build()

        val newCall = mClient.newCall(request)

        callMap.put(request.tag(), newCall)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                callback.onSuccess(response.body?.string())
            }

        })
    }


    inline fun <reified T> download(urlStr: String, callback: IHttpCallback) {


        val newURLStr = "${HOST_PRODUCT}$urlStr"
        val urlBuilder = newURLStr.toHttpUrl().newBuilder()

        val request = Request.Builder()
            .url(urlBuilder.build())
            .build()

        val newCall = mClient.newCall(request)

        callMap.put(request.tag(), newCall)
        newCall.enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                callback.onFailed(e.message)
            }

            override fun onResponse(call: Call, response: Response) {
                val bodyStr = response.body?.string()

                val strReader = StringReader(bodyStr)

//                val beanList = CsvToBeanBuilder<T>(strReader)
//                    .withSeparator('\t')
//                    .withType(T::class.java)
//                    .withIgnoreLeadingWhiteSpace(true)
//                    .build()
//                    .parse()

                val beanList = listOf<T>()
//                LogUtils.w("parse over ${beanList.size}", beanList.subList(0, 2))

                val bodyList = beanList.subList(1, beanList.size)

                callback.onSuccess(bodyList)
            }

        })
    }








    override fun cancelRequest(tag: Any) {
        callMap.get(tag)?.cancel()
    }


    override fun cancelAllRequest() {
        for (i in 0 until callMap.size()) {
            callMap.get(callMap.keyAt(i))?.cancel()
        }
    }

    fun get(params: Map<String, Any>, urlStr: String) = runBlocking {
        val urlBuilder = urlStr.toHttpUrl().newBuilder()
        params.forEach { entry ->
            urlBuilder.addEncodedQueryParameter(entry.key, entry.value.toString())
        }

        val request = Request.Builder()
            .get()
            .tag(params)
            .url(urlBuilder.build())
            .cacheControl(CacheControl.FORCE_NETWORK)
            .build()
        val newCall = mClient.newCall(request)

        callMap.put(request.tag(), newCall)
        newCall.call()
    }



    fun post(params: Map<String, Any>, uri: String) = runBlocking {
        val url = HOST_XML + uri

        var xmlBody = ""

        for((k, v) in params){
            xmlBody += "<$k>$v</$k>"
        }

        val xml = "<in>$xmlBody</in>"

        val reqBody = xml.toRequestBody("text/xml".toMediaType())

        val request = Request.Builder()
            .post(reqBody)
            .url(url)
            .tag(params)
            .build()

        val newCall = mClient.newCall(request)

        callMap.put(request.tag(), newCall)
        newCall.call()
    }


    inline fun <reified T> download(urlStr: String) = runBlocking {

        val newURLStr = "${HOST_PRODUCT}$urlStr"

        val urlBuilder = newURLStr.toHttpUrl().newBuilder()

        val request = Request.Builder()
            .url(urlBuilder.build())
            .build()

        val newCall = mClient.newCall(request)

        callMap.put(request.tag(), newCall)

        suspendCancellableCoroutine<List<T>> { continuation ->
            newCall.enqueue(object : Callback {
                override fun onFailure(call: Call, e: IOException) {
                    if (continuation.isCancelled) return
                    continuation.resumeWithException(e)
                }

                override fun onResponse(call: Call, response: Response) {

                    val bodyStr = response.body?.string()

                    val strReader = StringReader(bodyStr)

//                    val beanList = CsvToBeanBuilder<T>(strReader)
//                        .withSeparator('\t')
//                        .withType(T::class.java)
//                        .withIgnoreLeadingWhiteSpace(true)
//                        .build()
//                        .parse()
                    val beanList = listOf<T>()

                    continuation.resume(beanList)
                }
            })

            continuation.invokeOnCancellation {
                try {
                    newCall.cancel()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }



        // newCall.enqueue(object : Callback {
        //     override fun onFailure(call: Call, e: IOException) {
        //         callback.onFailed(e.message)
        //     }
        //
        //     override fun onResponse(call: Call, response: Response) {
        //         // val input = response.body?.byteStream()
        //         // val reader = BufferedReader(InputStreamReader(input))
        //         // for(ii in 0..3){
        //         //     val line = reader.readLine()
        //         //     LogUtils.i("line $line")
        //         // }
        //
        //         val bodyStr = response.body?.string()
        //         LogUtils.i("download response", bodyStr)
        //
        //         val strReader = StringReader(bodyStr)
        //         // val csvIter = CSVIterator(CSVReader(strReader))
        //         // for(row in csvIter){
        //         //
        //         // }
        //
        //         val beanList = CsvToBeanBuilder<T>(strReader)
        //             .withSeparator('\t')
        //             .withType(T::class.java)
        //             .withIgnoreLeadingWhiteSpace(true)
        //             .build()
        //             .parse()
        //
        //         for(bean in beanList){
        //             LogUtils.w("bean", bean)
        //         }
        //
        //
        //
        //         callback.onSuccess(bodyStr)
        //     }
        //
        // })

    }


    fun getACTK() = runBlocking {

        val jarx = LocalCookieJar()

        val userToken = "MySpUtils.getString(SP_KEY_USER_TOKEN)"
        val ssi = "MySpUtils.getString(SP_KEY_SSI)"

//        jarx.setCookie(getBaseHost(), "authenticatedToken", userToken)
        jarx.setCookie(getBaseHost(), "ssi", ssi)
//        jarx.setCookie(getBaseHost(), "dview", "ap")
//        jarx.setCookie(getBaseHost(), "ddevicediv", "ap20")
        val client = OkHttpClient().newBuilder().cookieJar(jarx).build()


//        val urlStr = originRequest.url.toString()
//        if(urlStr.contains("wos-chk")){
//            attachHeaders.add("Authorization" to
//                    Credentials.basic(
//                        "chk-test-wosusr",
//                        "p@48+tgG"))
//        }


        val url: String = URL_ACTK
        val requestBuilder = Request.Builder()

        if(url.indexOf("wos-chk") >= 0) {
            requestBuilder.addHeader(
                "Authorization",
                Credentials.basic(
                    "chk-test-wosusr",
                    "p@48+tgG"
                )
            )
        }


        val request = requestBuilder.url(url).get().build()



        val efs_vid0 = jarx.getCookie(getBaseHost(), "efs_vid")

        val newCall = client.newCall(request)
        newCall.call()

//        val efs_vid = jarx.getCookie(getBaseHost(), "efs_vid")
//        LogUtils.w("aaa")
    }




    private suspend fun Call.call(async: Boolean = true): String? {
        return suspendCancellableCoroutine { continuation ->
            if (async) {
                enqueue(object : Callback {
                    override fun onFailure(call: Call, e: IOException) {
                        //避免不必要的冗余调用
                        if (continuation.isCancelled) return
                        continuation.resumeWithException(e)
                    }

                    override fun onResponse(call: Call, response: Response) {
                        val cookies = response.headers.values("Set-Cookie")
                        cookies.forEach{
                            val kv = it.split(";")[0]
                            if(response.request.url.toString().indexOf("actk.html") >= 0 && kv.startsWith("efs_vid")){
                                val value = kv.split("=")[1]
//                                val logined = MySpUtils.getBoolean(SP_KEY_LOGINED)
                                val logined = false
                                val eid = "MySpUtils.getString(SP_KEY_CUSTOM_EFS_VID)"
                                if(!logined && eid == null){
                                    "MySpUtils.put(SP_KEY_CUSTOM_EFS_VID, value)"

                                    val sss = "MySpUtils.getString(SP_KEY_CUSTOM_EFS_VID)"
                                }
                            }
                        }

                        continuation.resume(response.body?.string())
                    }
                })
            } else {
                continuation.resume(execute().body?.string())
            }
            continuation.invokeOnCancellation {
                try {
                    cancel()
                } catch (ex: Exception) {
                    ex.printStackTrace()
                }
            }
        }
    }

}