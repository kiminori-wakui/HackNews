package jp.co.world.service.net

import com.example.hacknews.net.BaseResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface SearchWordService {

    @GET("/api/suggest/WOS?limit=10")
    fun getSuggestList(
        @Query("q_word") word: String? = null,
    ): Call<BaseResponse>


}