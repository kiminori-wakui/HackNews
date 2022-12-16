package com.example.hacknews.net

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginxService {

    @POST("auth/login/")
    fun login(@Body reqBody: LoginxReqBody): Call<BaseResponse>
}