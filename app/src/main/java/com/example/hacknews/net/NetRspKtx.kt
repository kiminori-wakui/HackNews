package com.example.hacknews.net

import jp.co.world.common.model.DataResult
import jp.co.world.common.model.succeeded
import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.InvocationKind
import kotlin.contracts.contract


inline fun <reified T> BaseResponse.toEntity(): T? {
    if (data == null) {
        return null
    }

    return null

//    return kotlin.runCatching {
//        // if (data is JsonObject){
//        // } else if (data is JsonArray) {
//        //     return GsonUtils.fromJson(Gson())
//        // }
//
//        if(data is String){
//            val jstr = data as String
//            GsonUtils.fromJson(jstr, T::class.java)
//        }else{
//            GsonUtils.fromJson(Gson().toJson(data), T::class.java)
//        }
//
//             // GsonUtils.fromJson(Gson().toJson(data), T::class.java)
//    }.onFailure { e ->
//        e.printStackTrace()
//    }.getOrNull()
}

@OptIn(ExperimentalContracts::class)
inline fun BaseResponse.onBizError(crossinline block: (code: Int, message: String?) -> Unit): BaseResponse {
    contract {
        callsInPlace(block, InvocationKind.AT_MOST_ONCE)
    }
    if (code != BaseResponse.SERVER_CODE_SUCCESS1 && code != BaseResponse.SERVER_CODE_SUCCESS) {
        block.invoke(code, message ?: "Error Message Null")
    }
    return this
}



@OptIn(ExperimentalContracts::class)
inline fun <reified T> BaseResponse.onBizOK(crossinline action: (code: Int, data: T?, message: String?) -> Unit): BaseResponse {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }


//     LogUtils.w("biz ok $data")

    if (code == BaseResponse.SERVER_CODE_SUCCESS || code == BaseResponse.SERVER_CODE_SUCCESS1) {
        action.invoke(code, this.toEntity<T>(), message)
    }
    return this
}


@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onSuccess(action: R.() -> Unit): DataResult<R> {
    //契约关系
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE) //最多走一次
    }
    if (succeeded) action.invoke((this as DataResult.Success).data)
    return this
}


@OptIn(ExperimentalContracts::class)
inline fun <R> DataResult<R>.onFailure(action: (exception: Throwable) -> Unit) : DataResult<R> {
    contract {
        callsInPlace(action, InvocationKind.AT_MOST_ONCE)
    }
    if (this is DataResult.Error) action.invoke(exception)
    return this
}

