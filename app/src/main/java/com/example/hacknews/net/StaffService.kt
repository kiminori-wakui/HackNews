package com.example.hacknews.net

import com.example.hacknews.net.BaseResponse
import jp.co.world.common.utils.MERCHANT_ID
import retrofit2.http.GET
import retrofit2.Call
import retrofit2.http.Query
import retrofit2.http.Url

interface StaffService {

    @GET("v1/coordinate?count=20&sort=time&merchant_id=${MERCHANT_ID}")
    fun getCoordByGoods(
        @Query("product_code") product_code: String?,
        @Query("exclude_coordinate_genre") exclude_coordinate_genre: String = "4,5,6,7"
    ): Call<BaseResponse>






    @GET("v1/coordinate/")
    fun getCoordinateList(
        @Query("gender") gender: String?,
        @Query("shop_code") shop_code: String?,
        @Query("product_code") product_code: String?,
        @Query("coordinate_label_id") coordinate_label_id: String?,
        @Query("user_id") user_id: String?,
        @Query("cid") cid: String?,
        @Query("from_height") from_height: String?,
        @Query("to_height") to_height: String?,
        @Query("from_age") from_age: String?,
        @Query("to_age") to_age: String?,
        @Query("sort") sort: String?,
        @Query("count") count: Int = 30,
        @Query("ext") ext: String?,
        @Query("label") label: String?,
        @Query("parent_category") parent_category: String?,
        @Query("offset") offset: Int = 0,
        @Query("merchant_id") merchant_id: String = MERCHANT_ID,
        @Query("exclude_coordinate_genre") exclude_coordinate_genre: String = "4,5,6,7"
    ): Call<BaseResponse>

    @GET("v1/coordinate/")
    fun getCoordinateListByUserId(
        @Query("user_id") user_id: String = "",
        @Query("count") count: Int = 120,
        @Query("merchant_id") merchant_id: String = MERCHANT_ID
    ): Call<BaseResponse>

    @GET("v1/coordinate/")
    fun getCoordinateListByLabelId(
        @Query("coordinate_label_id") coordinate_label_id: String = "",
        @Query("merchant_id") merchant_id: String = MERCHANT_ID,
        @Query("exclude_coordinate_genre") exclude_coordinate_genre: String = "4,5,6,7"
    ): Call<BaseResponse>

    @GET("v1/coordinate/")
    fun getCoordinateListByGenderAndLabel(
        @Query("gender") gender: String = "",
        @Query("coordinate_label_id") coordinate_label_id: String = "",
        @Query("merchant_id") merchant_id: String = MERCHANT_ID,
        @Query("exclude_coordinate_genre") exclude_coordinate_genre: String = "4,5,6,7"
    ): Call<BaseResponse>

    @GET("v1/coordinate/detail")
    fun getCoordinateDetail(
        @Query("cid") cid: String = "",
        @Query("merchant_id") merchant_id: String = MERCHANT_ID
    ): Call<BaseResponse>

    @GET("v1/staff/detail")
    fun getStuffDetail(
        @Query("user_id") user_id: String = "",
        @Query("merchant_id") merchant_id: String = MERCHANT_ID
    ): Call<BaseResponse>

    @GET("v1/coordinate/")
    fun getSnapItemsByGenderAndAge(
        @Query("gender") gender: String,
        @Query("from_height") from: Int,
        @Query("to_height") to: Int,
        @Query("merchant_id") merchant_id: String = MERCHANT_ID,
        @Query("exclude_coordinate_genre") exclude_coordinate_genre: String = "4,5,6,7"
    ): Call<BaseResponse>

    @GET("v1/coordinate/")
    fun getSnapItemsByGenderAndHeight(
        @Query("gender") gender: String,
        @Query("from_age") from: Int,
        @Query("to_age") to: Int,
        @Query("merchant_id") merchant_id: String = MERCHANT_ID,
        @Query("exclude_coordinate_genre") exclude_coordinate_genre: String = "4,5,6,7"
    ): Call<BaseResponse>


    @GET
    fun getSnapItemsBySearchCondition(
        @Url url: String
    ): Call<BaseResponse>
}
