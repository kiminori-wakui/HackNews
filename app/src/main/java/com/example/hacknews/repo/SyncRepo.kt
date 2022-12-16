package com.example.hacknews.repo

import jp.co.world.common.network.OkHttpApi
import jp.co.world.common.network.support.IHttpCallback
import jp.co.world.common.utils.getBaseHost
import com.example.hacknews.net.GoodsDetailItem
import com.example.hacknews.net.GoodsDetailRsp
import com.google.gson.Gson

object SyncRepo {


//    fun appAuth(goodsId: String, callback: (GoodsDetailItem?) -> Unit) {
//
//        val oldSSI = MySpUtils.getString(SP_KEY_SSI)
//
//        val
//
//        val userToken = MySpUtils.getString(SP_KEY_USER_TOKEN)
//        if(userToken == null){
//            LogUtils.w("token null ssi")
//            return
//        }
//
//
//
//
//        val tokenParams = mapOf(
//            "storeId" to "WOS",
//            "osDiv" to "02",
//            "terminalNotificationId" to "testtest"
//        )
////        val res = OkHttpApi.getInstance().get(
////            tokenParams, "${getBaseHost()}api/v1/item/app/detail/${goodsId}/json"
////        )
////        val goodsDetail =
////            GsonUtils.fromJson(res.body!!.string(), GoodsDetailRsp::class.java)
//
//        OkHttpApi.getInstance().get(tokenParams, "${getBaseHost()}api/v1/item/app/detail/${goodsId}/json", object: IHttpCallback{
//            override fun onSuccess(data: Any?) {
//                val rspStr = data as String?
//                if(TextUtils.isEmpty(rspStr)){
//                    LogUtils.e("goods detail empty rsp", goodsId)
//                    val goodsDetail =
//                        GsonUtils.fromJson(rspStr, GoodsDetailRsp::class.java)
//                    callback(goodsDetail?.itemInfoXML?.item)
//                }
//
//            }
//
//            override fun onFailed(error: Any?) {
//                LogUtils.e("get detail failed", goodsId)
//            }
//
//        })
//
//
////        return goodsDetail?.itemInfoXML?.item
//    }







    fun getGoodsDetailItem(goodsId: String, callback: (GoodsDetailItem?) -> Unit) {
        val tokenParams = mapOf(
            "storeId" to "WOS",
            "osDiv" to "02",
            "terminalNotificationId" to "nil"
        )
//        val res = OkHttpApi.getInstance().get(
//            tokenParams, "${getBaseHost()}api/v1/item/app/detail/${goodsId}/json"
//        )
//        val goodsDetail =
//            GsonUtils.fromJson(res.body!!.string(), GoodsDetailRsp::class.java)

        OkHttpApi.getInstance().get(tokenParams, "${getBaseHost()}api/v1/item/app/detail/${goodsId}/json", object: IHttpCallback{
            override fun onSuccess(data: Any?) {
                val rspStr = data as String?
                val goodsDetail =
                    Gson().fromJson(rspStr, GoodsDetailRsp::class.java)
                    callback(goodsDetail?.itemInfoXML?.item)
            }

            override fun onFailed(error: Any?) {
            }
        })


    }



}