package com.example.hacknews.repo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hacknews.net.*
import jp.co.world.common.model.DataResult
import jp.co.world.common.model.SingleLiveData
import jp.co.world.common.network.KtRetrofit
import jp.co.world.common.network.OkHttpApi
import jp.co.world.common.network.support.serverData
import jp.co.world.common.utils.getBaseHost


class GlobalServiceRepo(val service: GlobalService, private val retrofit: KtRetrofit):
    IGlobalServiceRepo {


    private val _liveRecentTitle = MutableLiveData<String>()
    override val liveRecentTitle: LiveData<String>
        get() = _liveRecentTitle

    private val _likeTest = MutableLiveData<Int?>()
    override val liveTest: LiveData<Int?>
        get() = _likeTest

    private val _liveNail = MutableLiveData<Map<String, String>>()
    override val liveNail: LiveData<Map<String, String>>
        get() = _liveNail

    private val _cartInfo = MutableLiveData<CartInfoRsp>()
    override val cartInfo: LiveData<CartInfoRsp>
        get() = _cartInfo

    private val _liveMailMagazineLikeList = MutableLiveData<List<String>>()
    override val liveMailMagazineLikeList: LiveData<List<String>>
        get() = _liveMailMagazineLikeList

    private val _liveStoreStockList = MutableLiveData<List<StoreStock>>()
    override val liveStoreStockList: LiveData<List<StoreStock>>
        get() = _liveStoreStockList

    private val _liveStoreLikeList = MutableLiveData<List<String>>()
    override val liveStoreLikeList: LiveData<List<String>>
        get() = _liveStoreLikeList

    private val _likeList = MutableLiveData<ArrayList<String>>()
    override val likeList: LiveData<ArrayList<String>>
        get() = _likeList

    private val _likeSkuList = MutableLiveData<ArrayList<String>>()
    override val likeSkuList: LiveData<ArrayList<String>>
        get() = _likeSkuList

    private val _liveSSI = SingleLiveData<SSIRsp>()
    override val liveSSI: LiveData<SSIRsp>
        get() = _liveSSI

    private val _liveRegWebview = SingleLiveData<Boolean?>()
    override val liveRegWebview: LiveData<Boolean?>
        get() = _liveRegWebview


    private val _liveSnapTitle = MutableLiveData<String?>()
    override val liveSnapTitle: LiveData<String?>
        get() = _liveSnapTitle







    init {
        _liveSnapTitle.value = "スタイルスナップ"
        _liveRecentTitle.value = ""
    }




    override suspend fun resetSnapTitle() {
        _liveSnapTitle.postValue("スタイルスナップ")
    }

    override suspend fun updateSnapTitle(title: String){
        _liveSnapTitle.postValue(title)
    }

    override suspend fun updateRecentTitle(title: String) {
        _liveRecentTitle.postValue(title)
    }


    override fun updateRegWebView() {
        _liveRegWebview.postValue(true)
    }

    override fun updateSSI(ssi: String){
        retrofit.jar.setCookie(getBaseHost(), "ssi", ssi)
        OkHttpApi.getInstance().jar.setCookie(getBaseHost(), "ssi", ssi)
    }





    override suspend fun updateNail(nails: Map<String, String>) {
        _liveNail.value = nails
    }


    override suspend fun setTest(value : Int) {
        _likeTest.value = value
    }

    override suspend fun getBrandNail(brandId: String): DataResult<BaseResponse> {
        return service.getBrandNail(brandId)
            .serverData()
    }
}

































