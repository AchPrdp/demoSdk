package com.app.mylibrary.util


/**
 * A Generic function responsible for parsing api call response.
 */
sealed class NetworkResult<T>(
    val data: T? = null,
    val errorMessage: String? = null,
    val exception: Exception? = null
) {
    class Success<T>(data: T) : NetworkResult<T>(data)
    class Error<T>(message: String? = null, data: T? = null, exception: Exception? = null) :
        NetworkResult<T>(data = data, errorMessage = message, exception = exception)
}