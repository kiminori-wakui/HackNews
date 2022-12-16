package jp.co.world.common.network

import jp.co.world.common.network.support.IHttpCallback



interface HttpApi {

    fun get(params: Map<String, Any>, urlStr: String, callback: IHttpCallback)

    fun getSync(params: Map<String, Any>, urlStr: String): Any? {
        return Any()
    }

    fun post(body: Any, urlStr: String, callback: IHttpCallback)

    fun postSync(body: Any, urlStr: String): Any? = Any()

    fun cancelRequest(tag: Any)

    fun cancelAllRequest()
}


