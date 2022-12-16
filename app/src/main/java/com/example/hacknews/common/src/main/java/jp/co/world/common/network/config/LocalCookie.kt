package jp.co.world.common.network.config

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull


class LocalCookieJar : CookieJar {

    private val serverCookiesStore = mutableMapOf<String, ArrayList<Cookie>>()

    private val clientCookiesStore = mutableMapOf<String, ArrayList<Cookie>>()


    override fun loadForRequest(url: HttpUrl): List<Cookie> {
        var cookieList = arrayListOf<Cookie>()

        var serverCookieList = serverCookiesStore[url.host]
        var clientCookieList = clientCookiesStore[url.host]

//        if (serverCookieList != null) {
//            cookieList.addAll(serverCookieList)
//        }

        if(clientCookieList != null){
            cookieList.addAll(clientCookieList)
        }

        return cookieList
    }

    override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>) {
        val res = arrayListOf<Cookie>()
        res.addAll(cookies)
        serverCookiesStore.put(url.host, res)
    }

    public fun getCookie(url:String, key: String): String? {
        val cookies = serverCookiesStore.get(url)
        if (cookies != null) {
            cookies.forEach{
                if(key == it.name){
                    return it.value
                }
            }
        }
        return null;
    }

    // public fun delCookie(url: String, key: String) {
    //     val host = url.toHttpUrlOrNull()?.host
    //     if(host == null){
    //         return
    //     }
    //
    //     var cookieListForUrl = clientCookiesStore[host]
    //     if (cookieListForUrl != null) {
    //         for(storedCookie in cookieListForUrl){
    //             if(storedCookie)
    //         }
    //     }
    //
    //     clientCookiesStore.remove(host)
    //     serverCookiesStore.remove(host)
    // }

    public fun setCookie(url: String, key: String, value: String) {
        val httpUrl = url.toHttpUrlOrNull()
        if(httpUrl != null){
            val ck = Cookie.parse(httpUrl, key + "=" + value)
            if(ck == null){
                return
            }
            setCookie(url, ck)
        }
    }

    fun setCookie(url: String, cookie: Cookie) {
        val host = url.toHttpUrlOrNull()?.host
        if(host == null){
            return
        }
        var cookieListForUrl = clientCookiesStore[host]
        if (cookieListForUrl == null) {
            cookieListForUrl = arrayListOf()
            clientCookiesStore.put(host, cookieListForUrl)
        }

        var scookieListForUrl = serverCookiesStore[host]

        putCookie(cookieListForUrl, scookieListForUrl, cookie)
    }

    private fun putCookie(storedCookieList: ArrayList<Cookie>, serverCookieList: ArrayList<Cookie>?, newCookie: Cookie){
        var soldCookie:Cookie? = null
        if(serverCookieList != null) {
            for (storedCookie in serverCookieList) {
                val oldKey = storedCookie.name + storedCookie.path
                val newKey = newCookie.name + newCookie.path

                if (oldKey.equals(newKey)) {
                    soldCookie = storedCookie
                    break
                }
            }
            if(soldCookie != null){
                serverCookieList.remove(soldCookie)
            }
        }
        var oldCookie:Cookie? = null
        for(storedCookie in storedCookieList){
            val oldKey = storedCookie.name + storedCookie.path
            val newKey = newCookie.name + newCookie.path

            if(oldKey.equals(newKey)){
                oldCookie = storedCookie
                break
            }
        }

        if(oldCookie != null){
            storedCookieList.remove(oldCookie)
        }

        storedCookieList.add(newCookie)

    }






    companion object{
    }



}


















