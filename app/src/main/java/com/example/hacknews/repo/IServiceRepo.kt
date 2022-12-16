package com.example.hacknews.repo

import androidx.lifecycle.LiveData
import com.example.hacknews.net.*

interface IServiceRepo {

    val goodsDetailRsp: LiveData<GoodsDetailRsp?>

    val brandLikeOpRsp: LiveData<StatusCodeRsp>
    val likeOpRsp: LiveData<StatusCodeRsp>
    val cartAdd: LiveData<CartAddRsp?>
    val actk: LiveData<String?>
    val magazineAdd: LiveData<RcdCodeRsp?>

    val liveSuggest: LiveData<ArrayList<String>>

    val liveSize: LiveData<ArrayList<SizeItemBean>>
    val liveFilterColor: LiveData<ArrayList<String>>

    val liveFavoBrandFailureID: LiveData<String?>



    suspend fun getSuggest(keyword: String)

    suspend fun getGoodsDetail(goodsId: String)




    suspend fun dislikeGoods(skuId: String)
    suspend fun likeGoods(skuId: String)


    suspend fun cartAdd(skuId: String)


    suspend fun getACTK()
    suspend fun magazineAdd(brandId: String?, mailMagazineId: String?=null, shopId: String?)
    suspend fun magazineRemove(brandId: String?, mailMagazineId: String?=null)

}