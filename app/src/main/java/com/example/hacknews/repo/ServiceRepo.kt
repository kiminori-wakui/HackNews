package com.example.hacknews.repo

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.hacknews.net.*
import com.example.hacknews.common.src.main.java.jp.co.world.common.config.SP_KEY_MAIL_ADDRESSS
import jp.co.world.common.model.SingleLiveData
import jp.co.world.common.network.OkHttpApi
import jp.co.world.common.network.support.serverData
import jp.co.world.common.utils.MySpUtils
import jp.co.world.service.net.*

class ServiceRepo(val service: ServiceService, val wordService: SearchWordService): IServiceRepo {

    private val _brandLikeOpRsp = SingleLiveData<StatusCodeRsp>()
    override val brandLikeOpRsp: LiveData<StatusCodeRsp>
        get() = _brandLikeOpRsp

    private val _likeOpRsp = SingleLiveData<StatusCodeRsp>()
    override val likeOpRsp: LiveData<StatusCodeRsp>
        get() = _likeOpRsp

    private val _goodsDetailRsp = SingleLiveData<GoodsDetailRsp?>()
    override val goodsDetailRsp: LiveData<GoodsDetailRsp?>
        get() = _goodsDetailRsp


    private val _cartAdd = SingleLiveData<CartAddRsp?>()
    override val cartAdd: LiveData<CartAddRsp?>
        get() = _cartAdd


    private val _magazineAdd = SingleLiveData<RcdCodeRsp?>()
    override val magazineAdd: LiveData<RcdCodeRsp?>
        get() = _magazineAdd

    private val _actk = SingleLiveData<String?>()
    override val actk: LiveData<String?>
        get() = _actk


    private val _liveSize = MutableLiveData<ArrayList<SizeItemBean>>()
    override val liveSize: LiveData<ArrayList<SizeItemBean>>
        get() = _liveSize

    private val _liveFilterColor = MutableLiveData<ArrayList<String>>()
    override val liveFilterColor: LiveData<ArrayList<String>>
        get() = _liveFilterColor

    private val _liveSuggest = MutableLiveData<ArrayList<String>>()
    override val liveSuggest: LiveData<ArrayList<String>>
        get() = _liveSuggest

    private val _liveFavoBrandFailureID = MutableLiveData<String?>()
    override val liveFavoBrandFailureID: LiveData<String?>
        get() = _liveFavoBrandFailureID

    override suspend fun getSuggest(keyword: String) {




//        val suggestList = arrayListOf<String>("$keyword AAA", "$keyword BBB", "$keyword CCCC", "$keyword DDDD")
//
//        _liveSuggest.value = suggestList

        wordService.getSuggestList(keyword)
            .serverData()
            .onSuccess {

                onBizError{ code, message ->
                }
                onBizOK<SearchSuggestRsp> { code, data, message ->
                    // _loginRsp.value = data
                    val suggests = data?.cateId?.map { it.label } ?: listOf()
                    val sArr = arrayListOf<String>()
                    sArr.addAll(suggests)
                    _liveSuggest.value = sArr

                    return@onBizOK
                }

            }
            .onFailure {
            }


    }





    override suspend fun getGoodsDetail(goodsId: String) {
        service.getGoodsDetail(goodsId)
            .serverData()
            .onSuccess {
                onBizError{ code, message ->
                }
                onBizOK<GoodsDetailRsp> { code, data, message ->

                    _goodsDetailRsp.value = data

                    return@onBizOK
                }

            }
            .onFailure {
            }
    }

    override suspend fun likeGoods(skuId: String){
        // retrofit.jar.setCookie()
        service.likeGoods(skuId)
            .serverData()
            .onSuccess {
                onBizError{ code, message ->
                }
                onBizOK<StatusCodeRsp> { code, data, message ->

                    _likeOpRsp.value = data

                    return@onBizOK
                }

            }
            .onFailure {
            }
    }


    override suspend fun dislikeGoods(skuId: String){
        service.dislikeGoods(skuId)
            .serverData()
            .onSuccess {
                onBizError{ code, message ->
                }
                onBizOK<StatusCodeRsp> { code, data, message ->

                    _likeOpRsp.value = data

                    return@onBizOK
                }

            }
            .onFailure {
            }
    }


    override suspend fun getACTK() {
        service.getACTK()
            .serverData()
            .onSuccess {

            }
            .onFailure {
            }
    }


    override suspend fun magazineAdd(brandId: String?, mailMagazineId: String?, shopId: String?){
        val res = OkHttpApi.getInstance().getACTK()
        val body = res //.body

        if(body == null){
            return
        }

        val htmlStr = body //.string()

        Log.d("actk html", htmlStr)

        val regex = Regex("""var actk = "(.*)";""")
        val match = regex.find(htmlStr)

        if(match?.groups?.size ?: 0 <= 1){
            return
        }

        val actk = match?.groups?.get(1)?.value ?: ""
        Log.d("actk", actk)

        val mmd = if(brandId != null) 13 else 14


        service.magazineAdd(mmd=mmd, rs=shopId, br=brandId, mailMagazineId=mailMagazineId, actk = actk)
            .serverData()
            .onSuccess {
                onBizError{ code, message ->
                }
                onBizOK<RcdCodeRsp> { code, data, message ->

                    _magazineAdd.value = data
                    if (data == null) {
                        _liveFavoBrandFailureID.value = brandId
                    }

                    return@onBizOK
                }

            }
            .onFailure {
            }


    }

    override suspend fun magazineRemove(brandId: String?, mailMagazineId: String?) {
        val res = OkHttpApi.getInstance().getACTK()
        val body = res //.body

        if(body == null){
            return
        }

        val htmlStr = body //.string()

        Log.d("actk html", htmlStr)

        val regex = Regex("""var actk = "(.*)";""")
        val match = regex.find(htmlStr)

        if(match?.groups?.size ?: 0 <= 1){
            return
        }

        val actk = match?.groups?.get(1)?.value ?: ""
        Log.d("actk", actk)

        val loginEmail = MySpUtils.getString(SP_KEY_MAIL_ADDRESSS) ?: " "

        service.magazineRemove(mail=loginEmail ,mailMagazineId=mailMagazineId, actk = actk)
            .serverData()
            .onSuccess {
                onBizError{ code, message ->
                }
                onBizOK<RcdCodeRsp> { code, data, message ->

                    _liveFavoBrandFailureID.value = brandId
                    return@onBizOK
                }
            }
            .onFailure {
            }
    }

    override suspend fun cartAdd(skuId: String){
        // retrofit.jar.setCookie()
        service.cartAdd(skuId)
            .serverData()
            .onSuccess {
                onBizError{ code, message ->
                }
                onBizOK<CartAddRsp> { code, data, message ->

                    _cartAdd.value = data

                    return@onBizOK
                }

            }
            .onFailure {
            }
    }




}

































