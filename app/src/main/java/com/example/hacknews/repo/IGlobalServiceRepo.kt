package com.example.hacknews.repo

import androidx.lifecycle.LiveData
import com.example.hacknews.net.BaseResponse
import com.example.hacknews.net.CartInfoRsp
import com.example.hacknews.net.SSIRsp
import com.example.hacknews.net.StoreStock
import jp.co.world.common.model.DataResult

interface IGlobalServiceRepo {
    val liveSnapTitle: LiveData<String?>

    val liveTest: LiveData<Int?>

    val liveRecentTitle: LiveData<String>

    val liveSSI: LiveData<SSIRsp>

    val liveMailMagazineLikeList: LiveData<List<String>>
    val liveStoreStockList: LiveData<List<StoreStock>>
    val liveStoreLikeList: LiveData<List<String>>
    val likeList: LiveData<ArrayList<String>>
    val likeSkuList: LiveData<ArrayList<String>>
    val cartInfo: LiveData<CartInfoRsp>

    val liveNail: LiveData<Map<String, String>>

    val liveRegWebview: LiveData<Boolean?>



    suspend fun resetSnapTitle()
    suspend fun updateSnapTitle(title: String)
    suspend fun updateRecentTitle(title: String)

    fun updateRegWebView()
    fun updateSSI(ssi: String)

    suspend fun setTest(value : Int)

    suspend fun updateNail(nails: Map<String, String>)
    suspend fun getBrandNail(brandId: String): DataResult<BaseResponse>
}