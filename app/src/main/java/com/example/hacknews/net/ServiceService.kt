package com.example.hacknews.net

import retrofit2.Call
import retrofit2.http.*

interface ServiceService {

    @GET("api/v1/item/app/search/json")
    fun getGoodsList(
        @Query("page") page: Int = 1,
        @Query("sicd") sicd: String? = null,
        @Query("br") brand: String? = null,
        @Query("it") it: String? = null,
        @Query("lm") lm: String? = null,
        @Query("ta") ta: String? = null,
        @Query("so") so: String? = null,
        @Query("si") si: String? = null,
        @Query("fr") fr: String? = null,
        @Query("co") co: String? = null,
        @Query("pl") pl: String? = null,
        @Query("pu") pu: String? = null,
        @Query("dl") dl: String? = null,
        @Query("du") du: String? = null,
        @Query("sst1") sst1: String? = null,
        @Query("st") st: String? = null,
        @Query("ar") ar: String? = null,
        @Query("un") un: String? = null,
        @Query("cpg") cpg: String? = null,
        @Query("myextpara") myextpara: String? = null,
        @Query("limit") limit: Int? = null,
    ): Call<BaseResponse>

    @GET("api/v1/item/app/detail/{goodsId}/json")
    fun getGoodsDetail(@Path("goodsId") goodsId: String): Call<BaseResponse>

    @FormUrlEncoded
    @POST("member/api/favorite-item?op=favorite")
    fun likeGoods(@Field("fid") fid: String): Call<BaseResponse>

    @FormUrlEncoded
    @POST("member/api/favorite-item?op=disfavorite")
    fun dislikeGoods(@Field("fids") fids: String): Call<BaseResponse>



    @FormUrlEncoded
    @POST("order/api/cart?op=add")
    fun cartAdd(@Field("ssc") ssc: String, @Field("amnt") amnt: Int=1): Call<BaseResponse>

    @FormUrlEncoded
    @POST("/mail/v1/api/register")
    fun magazineAdd(
        @Field("mail") mail: String = " ",
        @Field("mailMagazineId") mailMagazineId: String? = null,
        @Field("mmd") mmd: Int,
        @Field("br") br: String? = null,
        @Field("rs") rs: String? = null,
        @Field("actk") actk: String
    ): Call<BaseResponse>

    @FormUrlEncoded
    @POST("/mail/v1/api/cancel")
    fun magazineRemove(
        @Field("mail") mail: String = " ",
        @Field("mailMagazineId") mailMagazineId: String? = null,
        @Field("actk") actk: String
    ): Call<BaseResponse>

    @GET("app/actk.html")
    fun getACTK(): Call<BaseResponse>
}


























