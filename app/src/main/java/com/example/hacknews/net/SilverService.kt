package com.example.hacknews.net

import com.example.hacknews.net.BaseResponse
import retrofit2.Call
import retrofit2.http.*

interface SilverService {

    @GET("pycre5/jsonp/recommend?merch=worldfcl&spec=ap111&num=10&device=s")
    fun getSuggestList(
        @Query("cookie") cookie: String?
    ): Call<BaseResponse>


    @GET("pycre5/jsonp/recommend?merch=worldfcl&spec=ap311&num=10")
    fun getSuggestListByGoods(
        @Query("cookie") cookie: String?,
        @Query("cust") cust: String?,
        @Query("prod") prod: String?
    ): Call<BaseResponse>



}


























