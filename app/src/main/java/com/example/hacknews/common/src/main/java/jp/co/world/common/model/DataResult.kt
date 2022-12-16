package jp.co.world.common.model

import java.lang.Exception

sealed class DataResult<out R> {
    data class Success<out T>(val data: T) : DataResult<T>()

    data class Error(val exception: Exception) : DataResult<Nothing>()

    object Loading : DataResult<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
            Loading -> "Loading"
            else -> "Error"
        }
    }
}

val DataResult<*>.succeeded: Boolean
    get() = this is DataResult.Success && data != null


enum class Status{
    SUCCESS,
    ERROR,
    LOADING
}

data class ReSource<out T>(val status: Status, val data: T?, val message:String?){
   companion object{
       fun <T> success(data:T?): ReSource<T> {
           return ReSource(
               Status.SUCCESS,
               data,
               "Resource Success"
           )
       }
       fun <T> error(msg:String,data:T?): ReSource<T> {
           return ReSource(
               Status.ERROR,
               data,
               msg
           )
       }
       fun <T> loading(data:T?): ReSource<T> {
           return ReSource(
               Status.LOADING,
               data,
               null
           )
       }
   }
}
//endregion