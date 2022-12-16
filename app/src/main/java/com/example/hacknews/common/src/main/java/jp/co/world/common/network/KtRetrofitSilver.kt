package jp.co.world.common.network

import jp.co.world.common.network.support.LiveDataCallAdapterFactory
import com.zsk.common.network.config.KtHttpLogInterceptor
import jp.co.world.common.network.config.SJsonRequestInterceptor
import jp.co.world.common.network.config.SResponseInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object KtRetrofitSilver {

    private val mOkClient:OkHttpClient=OkHttpClient.Builder()
        // .callTimeout(10, TimeUnit.SECONDS)
        // .connectTimeout(10, TimeUnit.SECONDS)
        // .readTimeout(10, TimeUnit.SECONDS)
        // .writeTimeout(10, TimeUnit.SECONDS)
        .retryOnConnectionFailure(true)
        .followRedirects(false)
//        .cookieJar(LocalCookieJar())
//        .addInterceptor(SHostInterceptor())
        .addNetworkInterceptor(SJsonRequestInterceptor())
        .addNetworkInterceptor(SResponseInterceptor())
        .addNetworkInterceptor(KtHttpLogInterceptor {
            logLevel(KtHttpLogInterceptor.LogLevel.BODY)
        })
        .build()

    private val retrofitBuilder = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(LiveDataCallAdapterFactory())
        .client(mOkClient)

    private var retrofit:Retrofit?=null

    fun initConfig(baseUrl:String,okClient:OkHttpClient= mOkClient): KtRetrofitSilver {
        retrofit = retrofitBuilder.baseUrl(baseUrl).client(mOkClient).build()
        return this
    }

    fun <T> getService(serviceClazz:Class<T>):T{
        if (retrofit ==null){
            throw UninitializedPropertyAccessException("Retrofit必须初始化，需要配置baseUrl")
        }else{
            return retrofit!!.create(serviceClazz)
        }
    }
}