package com.app.mylibrary.util

import retrofit2.Response


/**
 * A Generic function responsible for api call.
 */
suspend inline fun <ResultType> networkBoundResource(
    crossinline apiCall: suspend () -> Response<ResultType>,
    crossinline shouldFetch: () -> Boolean = { true }
): NetworkResult<ResultType> {
    return if (shouldFetch()) {
        try {
            val response = apiCall()
            NetworkResult.Success(data = response.body()!!)
        } catch (e: Exception) {
            NetworkResult.Error(e.localizedMessage.orEmpty())
        }
    } else {
        NetworkResult.Error("No Internet Connection")
    }
}