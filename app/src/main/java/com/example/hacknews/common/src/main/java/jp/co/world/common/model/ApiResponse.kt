package jp.co.world.common.model

import retrofit2.Response

sealed class ApiResponse<T> {
    companion object {
        fun <T> create(response: Response<T>): ApiResponse<T> {
            return if (response.isSuccessful) {
                val body: T? = response.body()
                if (body == null || response.code() == 204) {
                    ApiEmptyResponse()
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                ApiErrorResponse(
                    response.code(),
                    response.errorBody()?.string() ?: response.message()
                )
            }
        }

        fun <T> create(errorCode: Int, error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(
                errorCode,
                error.message ?: "Unknown Error!"
            )

        }
    }
}

class ApiEmptyResponse<T> : ApiResponse<T>()

data class ApiErrorResponse<T>(val errorCode: Int, val errorMessage: String) : ApiResponse<T>()

data class ApiSuccessResponse<T>(val body: T) : ApiResponse<T>()

internal const val UNKNOWN_ERROR_CODE = -1