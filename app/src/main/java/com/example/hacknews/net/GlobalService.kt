package com.example.hacknews.net

import retrofit2.Call
import retrofit2.http.*

interface GlobalService {


    @GET("api/v1/item/search/json")
    fun getBrandNail(
        @Query("it") it: String? = null,
        @Query("limit") page: Int = 1
    ): Call<BaseResponse>

    @FormUrlEncoded
    @POST("/api/v1/mail-magazine/search/json")
    fun getMailMagazineLikeList(
        @Field("customerId") customerId: String,
        @Field("divs") divs: String
    ): Call<BaseResponse>

    @FormUrlEncoded
    @POST("/api/v1/item-real-store-stock/search/json")
    fun getStoreStock(
        @Field("biCd") biCd: String,
        @Field("latitude") latitude: Double? = null,
        @Field("longitude") longitude: Double? = null
    ): Call<BaseResponse>

    @GET("member/api/favorite-item?op=getItemCodeList")
    fun getLikeIdList(
    ): Call<BaseResponse>

    @GET("order/api/cart?op=display")
    fun cartInfo(): Call<BaseResponse>

    @GET("app/member/authenticate")
    fun getSSI(): Call<BaseResponse>

}


























