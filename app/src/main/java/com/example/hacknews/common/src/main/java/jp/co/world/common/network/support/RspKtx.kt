package jp.co.world.common.network.support

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import jp.co.world.common.model.ApiResponse
import jp.co.world.common.model.DataResult
import jp.co.world.common.model.UNKNOWN_ERROR_CODE
import com.google.gson.Gson
import okhttp3.Response
import retrofit2.Call
import retrofit2.Callback
import retrofit2.await
import retrofit2.awaitResponse
import java.io.IOException
import java.lang.RuntimeException


inline fun <reified T> okhttp3.Call.toLiveData(): LiveData<T?> {
    val live = MutableLiveData<T?>()
    this.enqueue(object : okhttp3.Callback {
        override fun onFailure(call: okhttp3.Call, e: IOException) {
            live.postValue(null)
        }

        override fun onResponse(call: okhttp3.Call, response: Response) {
            if (response.isSuccessful) {
                response.toEntity<T>()
            }
        }
    })
    return live
}

inline fun <reified T> Response.toEntity(): T? {
    if (!isSuccessful) return null
    if (T::class.java.isAssignableFrom(String::class.java)) {
        return kotlin.runCatching {
            this.body?.string()
        }.getOrNull() as? T
    }
    return kotlin.runCatching {
        Gson().fromJson(this.body?.string(), T::class.java)
    }.onFailure { e ->
        e.printStackTrace()
    }.getOrNull()
}
//endregion

//region retrofit相关扩展

fun <T : Any> Call<T>.toLiveData(): LiveData<T?> {
    val live = MutableLiveData<T?>()
    this.enqueue(object : Callback<T> {
        override fun onFailure(call: Call<T>, t: Throwable) {
            live.postValue(null)
        }

        override fun onResponse(call: Call<T>, response: retrofit2.Response<T>) {
            val value: T? = if (response.isSuccessful) {
                response.body()
            } else {
                null
            }
            live.postValue(value)
        }
    })
    return live
}


suspend fun <T:Any> Call<T>.serverData():DataResult<T>{
    var result:DataResult<T> = DataResult.Loading
    kotlin.runCatching {
        this.await()
    }.onFailure {
        result=DataResult.Error(RuntimeException(it))
    }.onSuccess {
        result=DataResult.Success(it)
    }
    return result
}


suspend fun <T : Any> Call<T>.serviceRsp(): ApiResponse<T> {
    var result: ApiResponse<T>
    val response: retrofit2.Response<T> = kotlin.runCatching {
        this.awaitResponse()
    }.onFailure {
        result = ApiResponse.Companion.create(UNKNOWN_ERROR_CODE, it)
        it.printStackTrace()
    }.getOrThrow()
    result = ApiResponse.create(response)
    return result
}
//endregion