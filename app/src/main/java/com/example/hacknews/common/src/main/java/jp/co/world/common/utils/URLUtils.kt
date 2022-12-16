package jp.co.world.common.utils

import java.net.URLDecoder

object URLUtils {
    @JvmStatic
    fun getQueriesFromURI(queryStr:String): HashMap<String, String?> { //it=WB06&st=1&sst1=2
        val params = queryStr.split("&")
        val queryMap = HashMap<String, String?>()
        for(pair in params){
            val idx = pair.indexOf("=")
            val key = if (idx > 0) URLDecoder.decode(pair.substring(0, idx), "UTF-8") else pair
            val value = if (idx > 0 && pair.length > idx + 1) URLDecoder.decode(
                pair.substring(idx + 1),
                "UTF-8"
            ) else null

            queryMap[key] = value
        }

        return queryMap
    }

}